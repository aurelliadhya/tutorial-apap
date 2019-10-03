# Tutorial APAP

## Authors

* **Aurellia Dhya Andini** - *1706022022* - *C*

---
## Tutorial 1
### What I have learned today

#### Github
1. Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker?
    Issue Tracker adalah suatu fitur untuk melacak masalah yang timbul pada kode yang sudah dibuat, melaporkan adanya error, dan mengkoordinasi pekerjaan yang ingin diselesaikan. Masalah yang dapat diselesaikan dengan Issue Tracker adalah apabila terjadi bug atau error, adanya comment yang melanggar ketentuan github, serta terjadi double issues.

2. Apa perbedaan dari git merge dan merge --squash?
  Git merge dilakukan saat fitur terakhir yang telah dipush ke branch selesai dibuat yang kemudian dimerge ke branch master, sedangkan git merge --squash merupakan teknik penggabungan fitur pada branch dari awal hingga akhir sebelum dimerge ke dalam branch master.

#### Spring
3. Apa itu library & dependency?
    Library  adalah suatu set dari kode yang sebelumnya sudah dibuat, sehingga user dapat langsung menggunakannya dalam pembuatan program.
    Dependency adalah suatu ketergantungan fungsionalitas antara suatu objek dengan objek  lainnya, sehingga apabila terjadi perubahan pada salah satunya akan berdampak kepada objek lainnya. 

4. Apa itu Maven? Mengapa kita perlu menggunakan Maven?
  Maven merupakan Java Build Tools yang menggunakan konsep Project Object Model (POM). Kita perlu menggunakan Maven karena Maven memiliki beberapa fungsi yaitu dapat membuat struktur project sendiri yang dapat dibuka dengan berbagai IDE, mudah dalam pengaturan dependency, dan mempermudah user dalam mengimport class yang memiliki dependency pada program karena Maven dapat mendownload secara otomatis file-file yang sudah tersimpan dalam bentuk POM.xml ke dalam repository user.
  
5. Apa alternatif dari Maven?
  Alternatif dari Maven yaitu Ant dan Gradle.


### What I did not understand
- Keuntungan memakai Spring Boot dibanding Django.
- Fitur-fitur pada Github, contohnya issues (Saya belum terlalu mengerti fungsinya)
- Kegunaan dan cara menyusun RequestParam dan Path Variable

---
## Tutorial 2
### What I have learned today

1. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepon=14022
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
Url di atas tidak dapat dijalankan karena html "add-restoran" belum dibuat, sehingga controller tidak dapat merender html.

2. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi
Perintah add tidak berhasil dilakukan karena tidak terdapat variabel nomorTelepon pada url.

3. Jika Papa APAP ingin melihat restoran PanyuFC, link apa yang harus diakses?
http://localhost:8080/restoran/view?idRestoran=1

4. Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/restoran/viewall, apa yang akan ditampilkan?
Sertakan juga bukti screenshotmu
Halaman akan menampilkan 2 restoran yang sudah diadd sebelumnya beserta id, alamat, dan nomor telepon dari masing-masing restoran tersebut.
Link menuju bukti screenshot -> https://ibb.co/CPN2hPt

---
## Tutorial 3
### What I have learned today

1. Pada class MenuDb, terdapat method findByRestoranIdRestoran, apakah kegunaan dari method tersebut?
	Kegunaan dari method tersebut yaitu untuk mencari menu berdasarkan id restoran yang sudah ada di database.

2. Pada class RestoranController, jelaskan perbedaan method addRestoranFormPage dan addRestoranSubmit?
	Method addRestoranFormPage berfungsi untuk membuat object restoran baru yang kemudian dilanjutkan ke halaman form-add-restoran untuk pengisian data restoran oleh user, sedangkan method addRestoranSubmit berfungsi untuk mengambil nilai dari form yang sudah diisi oleh user untuk disimpan ke database melalui method yang ada di dalamnya.

3. Jelaskan apa kegunaan dari JPA Repository?
	Kegunaan dari JPA Repository adalah untuk menyediakan segala fungsi tanpa harus membuat query terlebih dahulu, sehingga dapat mempermudah developer dalam mengakses data

4. Sebutkan dan jelaskan di bagian kode mana sebuah relasi antara RestoranModel dan MenuModel dibuat?
	Pada bagian ini di MenuModel:

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restoranId", referencedColumnName = "idRestoran", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RestoranModel restoran;

    Terdapat sebuah kolom di tabel RestoranModel yang merefer ke kolom di tabel MenuModel yaitu idRestoran, sehingga restoran dapat memiliki banyak menu.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
	FetchType.LAZY: hibernate tidak memuat semua collection object (child) saat pengambilan data object parent, melainkan hanya dimuat jika dipanggil melalui getter method.
	CascadeType.ALL: mendapatkan semua data dari suatu object atau tabel, termasuk tabel yang memiliki relasi dengannya.
	FetchType.EAGER: hibernate memuat semua collection object (child) setelah data dari object parent diambil.

---
## Tutorial 4
### What I have learned today

1. Jelaskan yang anda pelajari dari melakukan latihan nomor 2, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 2
  Dari latihan 2 saya belajar untuk menggunakan reuse pada fragment dengan mengubah variabel sesuai dengan kebutuhan. Pertama, saya mencari di internet dan mendapatkan beberapa contoh kasus yang sekiranya bisa saya jadikan referensi untuk mengerjakan nomor tersebut. Setelah itu, saya mencoba mengimplementasikan referensi kode tadi dengan cara mengubah sedikit menjadi 
  <nav th:fragment="navbar(content)" class="navbar navbar-expand-lg navbar-light bg-light">
      <a th:text="${content}" class="navbar-brand" href="#"></a>

  Lalu, saya menyesuaikan 'content' dengan nama halaman masing-masing pada semua file html.

2. Jelaskan yang anda pelajari dari latihan nomor 3, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 3


3. Jelaskan perbedaan th:include dan th:replace
    th:include akan memasukkan fragment spesifik dari body host tag kecuali tag dari fragment itu sendiri, sedangkan th:replace akan mengganti host tag body dengan host tag yang dimiliki oleh fragment.

4. Jelaskan bagaimana penggunaan th:object beserta tujuannya
    th:object bertujuan untuk menunjukkan bahwa suatu action akan langsung terhubung dengan modelnya. Contohnya yaitu penggunaan th:object pada form yang setelah di-POST akan membuat sebuah object berdasarkan modelnya.
