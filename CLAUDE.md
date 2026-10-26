# CLAUDE.md — Device-Paralel-Testing

Appium + Cucumber + TestNG parallel runner untuk aplikasi Alfagift (iOS & Android).
Dokumen ini adalah **aturan wajib** untuk agent Claude yang menambah / mengubah test.
Baca sampai habis sebelum ngetik kode apapun.

---

## 1. Working directories

Hanya tiga direktori ini yang boleh ditambah / diubah untuk pekerjaan test fitur. Jangan
sentuh folder lain tanpa diminta eksplisit.

| Jenis file                     | Lokasi                                                                  |
|--------------------------------|-------------------------------------------------------------------------|
| Page object (production)       | `src/main/java/org/example/test/features/<feature>/`                    |
| Gherkin feature                | `src/test/resources/features/<feature>/<feature>.feature`               |
| Step bindings (Kotlin)         | `src/test/java/step/<feature>/<Feature>Step.kt`                         |

`<feature>` SELALU **lowercase, satu kata, singular**. Contoh valid: `home`, `login`,
`onboarding`. Contoh invalid: `HomePage`, `membership_card`, `memberships`.

---

## 2. Sebelum nambah page baru — cek duplikat

Jalankan dua perintah ini dulu:

```bash
ls src/main/java/org/example/test/features/
ls src/test/resources/features/
```

Jika folder dengan nama `<feature>` yang mau kamu bikin **sudah ada**:
- JANGAN bikin folder baru.
- JANGAN bikin page baru.
- Perluas page object yang ada (tambah locator / method), dan tambah scenario di
  `.feature` yang sudah ada.

Baru bikin folder baru kalau benar-benar fitur baru yang belum pernah ada.

---

## 3. Pola Page Object (3 file per feature)

Setiap feature wajib punya **3 file** di `src/main/java/org/example/test/features/<feature>/`:

### 3.1. `<Feature>Page.kt` — abstract, cross-platform
- Package: `org.example.test.features.<feature>`
- Signature: `abstract class <Feature>Page(driver: AppiumDriver): BaseTestPage(driver)`
- **WAJIB** extends `org.example.test.base.BaseTestPage`.
- Semua locator dideklarasikan `abstract val NAMA_UPPER_SNAKE_CASE: By` — tidak ada
  implementasi locator di sini.
- Semua **method aksi / assertion** (`tap...`, `input...`, `is...Visible()`,
  `waitUntil...Visible()`) ditulis di sini, pakai helper dari `BaseTestPage`
  (`tap`, `typeText`, `waitUntilPresent`, `isVisible`, `becomesVisible`, `findView`,
  `multiTap`). Jangan panggil `driver.findElement(...)` langsung — selalu lewat helper.
- Tidak boleh ada `@Component` kecuali memang di-inject lewat Spring (saat ini tidak).

### 3.2. `IOS<Feature>Page.kt` — override iOS
- Signature: `class IOS<Feature>Page(driver: IOSDriver): <Feature>Page(driver)`
- Hanya `override val LOCATOR: By = ...`. Tidak boleh nambah method baru di sini
  kecuali platform-specific (contoh: `hideKeyboard()` yang berbeda implementasinya).
- Locator iOS pakai **accessibility id** (`By.id("home.header.orderMethod")`) atau
  `AppiumBy.iOSNsPredicateString(...)` kalau tidak bisa pakai id. Format accessibility
  id iOS: `<page>.<section>.<element>` (titik sebagai separator).

### 3.3. `Android<Feature>Page.kt` — override Android
- Signature: `class Android<Feature>Page(driver: AndroidDriver): <Feature>Page(driver)`
- Locator Android pakai resource id (`By.id("tv_order_method")`).
- Kalau locator Android belum tersedia, pakai placeholder:
  `override val X: By = By.id("belum ada")` — JANGAN pakai `TODO(...)` di property
  initialiser karena bikin page crash saat di-instantiate di Android test.

---

## 4. Pola Step (`src/test/java/step/<feature>/<Feature>Step.kt`)

- Package: `step.<feature>` (lowercase).
- Signature: `class <Feature>Step: BaseStep<<Feature>Page>()`
- **WAJIB** extends `org.example.test.base.BaseStep<T: BaseTestPage>`.
- Override `targetPage` memakai `PageProvider.getPage(...)`:

```kotlin
override val targetPage: <Feature>Page =
    PageProvider.getPage(
        GlobalContext.getAppiumDriver(),
        ::Android<Feature>Page,
        ::IOS<Feature>Page
    )
```

- Setiap step method pakai anotasi Cucumber: `@Given`, `@When`, `@Then`, `@And` dari
  `io.cucumber.java.en.*`.
- String di anotasi (contoh `@Then("promo button should be visible")`) **harus persis
  sama** dengan step di file `.feature`. Case-sensitive. Parameter `{string}`
  dipetakan ke parameter method bertipe `String`.
- Assertion pakai `org.junit.jupiter.api.Assertions.assertTrue(...)` / sejenis.
- Semua interaksi UI lewat `targetPage.<method>()` — JANGAN panggil `driver`
  langsung dari step.
- Data sensitif (nomor identitas, password) ambil dari env var:
  `System.getenv("TEST_IDENTITY_NUMBER") ?: throw IllegalStateException(...)`.

---

## 5. Pola Gherkin (`.feature`)

Struktur minimal:

```gherkin
@<feature>_page

Feature: <Nama Feature>

  Background:
    Given <langkah umum semua scenario>

  @<feature> @<scope-opsional>
  Scenario: <Deskripsi singkat>
    Given ...
    When ...
    Then ...
```

Aturan:
- Baris pertama: tag level-feature (contoh `@onboarding_page`, `@login_apo`).
- Setiap `Scenario:` **wajib** punya minimal satu tag `@<feature>` di atasnya —
  tag ini dipakai `parallel_testing.xml` sebagai `cucumber.filter.tags`.
- Setiap step di scenario harus punya pasangan method di `<Feature>Step.kt`
  dengan anotasi string yang sama persis.
- Format tanpa `and` tanpa sinonim-sinonim lain. Pakai keyword Cucumber standard:
  `Given`, `When`, `Then`, `And`, `But`.

---

## 6. Urutan kerja wajib saat nambah / ubah test

Ikuti urutan ini. Jangan lompat.

### 6a. GATE 1 — Playback scenario (sebelum ngetik kode apapun)

Tujuan: pastikan kamu nguji **intent user**, bukan interpretasimu sendiri.

1. Baca scenario yang diminta user (pre-condition, langkah, expected result).
2. **Playback balik** dalam bahasa sendiri, maksimal 150 kata, mencakup:
   - Apa yang dites (invariant utama).
   - List elemen UI yang bakal disentuh / di-assert, dengan nama logis
     (contoh: "nama member", "tombol Barcode Member") — belum perlu id.
   - Assertion yang akan ditulis (apa yang membuktikan behaviour, bukan sekadar
     "halaman tampil").
3. **Stop. Tunggu user approve.** Satu scenario = satu `Then` yang kuat.
   Lebih baik 3 scenario spesifik satu invariant daripada 1 scenario raksasa
   yang gampang false-green.

### 6b. GATE 2 — Grounding ke source iOS (sebelum nulis `IOS<Feature>Page.kt`)

Tujuan: pastikan tiap accessibility id benar-benar ada di app, bukan hasil
karangan model.

4. Untuk tiap elemen di scenario, **grep source iOS** di
   `/Users/gli-davis/codes/sandbox2/alfagift-ios-new` cari pattern:
   - `.accessibilityIdentifier("...")`
   - `.accessibility(identifier: "...")`
   - `accessibilityIdentifier = "..."`
5. Jika semua ketemu → lanjut nulis locator persis sesuai id di source.
6. Jika ada yang **tidak ketemu** → STOP. Lapor user elemen mana yang
   belum punya accessibility id. **Jangan guess**, jangan pakai XPath
   bertingkat, jangan pakai nilai label sebagai id.

### 6c. Implementasi

7. **Tentukan `<feature>`.** Satu kata lowercase. Kalau user bilang "home page"
   → `home`. Kalau "membership card" → masih bagian dari `home` (jangan bikin
   folder `membership`), jadi perluas `home`.
8. **Cek duplikat** (section 2).
9. **Kalau folder page belum ada**: bikin 3 file sesuai section 3. Isi dulu
   abstract + iOS. Android pakai placeholder `By.id("belum ada")` kalau belum
   diminta.
10. **Kalau folder page sudah ada**: tambah `abstract val` locator baru +
    method baru di `<Feature>Page.kt`, override di `IOS<Feature>Page.kt`
    (dan Android kalau diminta).
11. **Edit / bikin `.feature`** sesuai pola section 5. Pastikan setiap step
    string masuk akal dan reusable.
12. **Edit / bikin `<Feature>Step.kt`** sesuai pola section 4. Setiap step di
    `.feature` → ada method dengan anotasi string yang sama.
13. **Verifikasi mapping**: untuk setiap `Given/When/Then/And` di `.feature`,
    grep step annotation dengan string yang sama. Kalau tidak ketemu, berarti
    belum nyambung — Cucumber akan skip / error.

### 6d. GATE 3 — Dry-run verifier (opsional, kalau user minta jalanin)

Default: **jangan jalankan test** kecuali user minta. Runner ini pakai device
fisik / simulator dan punya efek samping.

Kalau user minta jalankan dan test red:

14. Dump `driver.pageSource` dari step yang gagal. Cross-check elemen yang
    muncul vs locator yang dipakai.
15. Perbaiki locator / step. Retry.
16. Loop maksimal **3 kali**. Masih red → stop, escalate ke user dengan
    page source + screenshot elemen yang bermasalah. Jangan loosen assertion
    buat bikin ijo (lihat section 7).

---

## 7. Hal yang DILARANG

- Mengubah `BaseTestPage.kt`, `BaseStep.kt`, `PageProvider.kt`, `GlobalContext.kt`,
  `DeviceFarmRunner.kt`, `parallel_testing.xml` tanpa diminta eksplisit.
- Menghapus / mengubah locator / scenario yang sudah ada hanya karena "kelihatan
  nggak dipakai". Konfirmasi dulu ke user.
- Mengganti test assertion jadi lebih longgar supaya hijau. Kalau test red, perbaiki
  **production code** / locator-nya, bukan assertion-nya (test adalah source of truth).
- Pakai `Thread.sleep` sebagai pengganti wait eksplisit. Pakai `waitUntilPresent`
  atau `becomesVisible` dari `BaseTestPage`.
- Panggil `driver.findElement(...)` dari step atau page konkret. Semua lewat helper
  `BaseTestPage`.
- Hardcode nomor identitas / password / data sensitif di source. Pakai env var.

---

## 8. Referensi cepat

- `BaseTestPage` helper: `findView`, `tap`, `multiTap`, `typeText`, `isVisible`,
  `becomesVisible`, `waitUntilPresent`.
- `BaseStep<T>` kontrak: `abstract val targetPage: T`.
- `PageProvider.getPage(driver, ::AndroidPage, ::IOSPage)` memilih implementasi
  sesuai tipe driver yang aktif di thread.
- Driver aktif per thread diakses lewat `GlobalContext.getAppiumDriver()`.
