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
  Dari latihan 3 saya belajar untuk memasukkan beberapa row data sekaligus ke dalam database. Saya menambahkan params pada method di controller dan menyamakan namanya dengan button di html. Kemudian, saya menambahkan button 'Add row' yang terhubung pada method addRow untuk menambah row form dan button 'Hapus' untuk menghapus row. Lalu, saya membuat suatu arraylist untuk menampung barisan-barisan data yang akan disubmit oleh user. Setelah user memencet button submit, atribut dari menu-menu tersebut akan dimasukkan ke dalam arraylist tadi dan bersambung ke method addProductSubmit.

3. Jelaskan perbedaan th:include dan th:replace
  th:include akan memasukkan fragment spesifik dari body host tag kecuali tag dari fragment itu sendiri, sedangkan th:replace akan mengganti host tag body dengan host tag yang dimiliki oleh fragment.

4. Jelaskan bagaimana penggunaan th:object beserta tujuannya
  th:object bertujuan untuk menunjukkan bahwa suatu action akan langsung terhubung dengan modelnya. Contohnya yaitu penggunaan th:object pada form yang setelah di-POST akan membuat sebuah object berdasarkan modelnya.


---
## Tutorial 5
### What I have learned today

1. Jelaskan bagian mana saja dari test yang dibuat pada latihan no 2 adalah given, when, dan and then.
  Given: membuat 1 dummy restoran yang disimpan dalam variabel restoranModel
  When: dummy restoran dengan id = 1 dipanggil berdasarkan id
  Then: mengembalikan objek dummy tersebut

  Pada method yang sudah saya buat dilakukan pengecekan terhadap atribut yang seharusnya ada pada halaman view restoran, seperti string 'Informasi Restoran', 'ID Restoran', dan lain sebagainya. Selain itu, dilakukan juga pengecekan nilai dari atribut-atribut tersebut.

2. Jelaskan perbedaan line coverage dan logic coverage.
  Line coverage adalah tes yang memastikan bahwa setiap baris dari source code sudah dieksekusi, sedangkan logic coverage adalah tes yang memastikan kesesuaian dari setiap struktur internal kode terhadap logika.

3. Pada keadaan ideal, apa yang seharusnya dibuat terlebih dahulu, code atau unit test? Mengapa seperti itu? Apa akibatnya jika urutannya dibalik, adakah risiko tak terlihat yang mungkin terjadi?
  Sebenarnya, hal pertama yang sebaiknya dilakukan yaitu membuat unit test agar semua kode yang akan dibuat tercover oleh test dan meminimalisir terjadinya error. Risiko yang mungkin terjadi apabila urutan tersebut dibalik yaitu metode yang dibuat ternyata lebih kompleks dari yang seharusnya, sehingga menyebabkan sulitnya kode tersebut untuk dites. Selain itu, biasanya penulis kode akan lebih malas untuk membuat tes apabila implementasi kode telah berjalan dengan baik.

Link menuju screenshots (karena saya tidak berhasil mengupload foto):
1. Latihan nomor 1
   Sebelum: https://drive.google.com/open?id=1HVxtYSZQR4SBcArP-2ogxWKDUhMPK1T7
   Sesudah: https://drive.google.com/open?id=1N2wQJtCpBFilDtyL5md2l03MTE6In-WP

2. Latihan nomor 2
   Sebelum: https://drive.google.com/open?id=1c43_uUUn2Q8T2z-TAtSQR2AtbfHSwkHF
   Sesudah: https://drive.google.com/open?id=1ka2FdDj2IGywf4oW1b7ENW9uNLdDI2v1

---
## Tutorial 6
### What I have learned today

1. Apa itu postman? Apa kegunaan dari postman?
Postman adalah sebuah aplikasi untuk menguji keberhasilan dari REST API yang telah dibuat. Aplikasi ini berguna untuk developer yang berkutat dalam pembuatan API.

2. Apa kegunaan dari annotation @JsonIgnoreProperties?
Anotasi tersebut berfungsi untuk menggunakan serialisasi atau mengabaikan properti JSON.

3. Apa itu ResponseEntity dan apa kegunaannya?
ResponseEntity digunakan untuk melakukan konfigurasi respon HTTP dan mewakili seluruh respon dari HTTP yaitu kode status, header, dan body.

---
## Tutorial 7
### What I have learned today

1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode yang telah anda buat) konsep tersebut diimplementasi?
Otentikasi adalah proses mengidentifikasi pengguna dan memberikan akses ke dalam sistem, sedangkan otorisasi adalah proses memeriksa hak pengguna untuk mengakses sistem. Konsep tersebut diimplementasikan pada method configure di WebSecurityConfig.java.

2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerjanya! 
Salah satu fitur security dari spring yang berfungsi untuk meng-encode password dari user. BCryptPasswordEncoder akan mengganti password menjadi kode random agar tidak tertulis secara eksplisit di database.

3. Jelaskan secara singkat apa itu UUID dan mengapa kita memakai UUID di UserModel.java?
UUID adalah sekumpulan 32 karakter (String) random yang berfungsi untuk men-generate ID atau string yang unik setiap pembuatan UUID baru. Fungsi UUID pada UserModel.java adalah untuk menjaga keunikan data antar user serta meningkatkan keamanan user apabila terdapat hacker yang ingin mencoba melakukan hack pada akun user. Hacker tersebut akan sulit menebak data karena terdapat kombinasi 32 karakter yang tidak memiliki pola khusus, sehingga hacking akan membutuhkan waktu yang lama.

4. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut padahal kita sudah memiliki class UserRoleServiceImpl.java?
UserDetailsServiceImpl.java mengimplementasi UserDetailsService yang berfungsi untuk membuat autentikasi user dan menghasilkan objek UserDetail yang akan memberikan data user sesuai database dan memberikan otorisasi berdasarkan role yang dimiliki, sedangkan UserServiceImpl berfungsi untuk mengatur mapping yang dilakukan oleh user.

---
## Tutorial 8
### What I have learned today

1. Hilangkan checkbox pada item di list bagian kiri. Gunakan approach terbaik menurut Anda. Anda hanya tidak diperbolehkan untuk membuat component Item baru.
Saya membuat variabel "hide" untuk menandakan checkbox yang akan dihilangkan dan mana yang tidak. Lalu, saya menambahkan variabel tersebut dalam parameter fungsi List dan proses pemanggilan fungsi Item. Terakhir, saya menggunakan syntax hidden dan memasukkan variabel hide ke dalam syntax tersebut.
Screenshots: 
https://drive.google.com/open?id=1fCVpew9c5kMKASyJESkwFViKQzokuv8d
https://drive.google.com/open?id=1j7-hXkSYHsoGURz3dEhepibH0BBW4S0N
https://drive.google.com/open?id=1GQB7UXhxXTXQ6GViXNJ4a75jAgnzWY0V


2. Jika kalian memilih item dari bagian kiri dan item dengan ID yang sama sudah ditambahkan sebelumnya, maka item tersebut terhapus dari bagian kanan. Buatlah mekanisme agar bagian kiri hanya melakukan operasi add. Berikut adalah ilustrasi behavior sekarang.
Awalnya, saya mencoba menghapus kondisi else pada fungsi handleItemClick. Cara tersebut berhasil untuk membuat menu favorit tidak terhapus apabila saya melakukan klik pada item di sebelah kiri lebih dari sekali, namun menu favorit menjadi tidak dapat dihapus. Akhirnya, saya menambahkan parameter boolean "canDelete" pada fungsi handleItemClick pada App.js dan AppFunc.js yang berfungsi untuk menghapus menu favorit apabila diklik pada item sebelah kanan, serta mengubah kondisi menjadi else if. Lalu, saya menambahkan parameter "canDelete" tersebut pada saat pemanggilan fungsi handleItemClick pada kedua file tersebut.
Screenshots:
https://drive.google.com/open?id=1_SLGdFmYEKZfovSOy0PIiMf40OXg57V8
https://drive.google.com/open?id=1wwJrxhLyNWP-4UFa8-mXNiuKU3_QLEVF

3. Buatlah toggle dimana jika toggle ON, maka My Favorite ditampilkan. Jika toggle OFF, maka sembunyikan. Tata letak toggle tidak harus sama.
Saya menggunakan state checked untuk membedakan menu favorit yang sudah ada isinya dan dapat ditampilkan dengan mengklik checkbox "show favorite". Saya membuat fungsi handleChangeChecked untuk mengubah status checked yang bergantung pada keadaan checkbox apakah di-check atau tidak. Kemudian, terdapat fungsi showFavItem untuk menampilkan menu favorit yang sudah dipilih.
Screenshots:
https://drive.google.com/open?id=1yy-LAxDEkf76T7qaOt18UHgcpnXKeP_s
https://drive.google.com/open?id=1KM011wUOOlT4BvxFfxO2HK1ZFGs_XCsE
https://drive.google.com/open?id=1MeLXPoC02vUkXmo-ILpyYik2liSRrnp6

4. Jika daftar favorit kosong, maka tampilkan empty state. Hint: buat component bernama EmptyState.
Saya membuat component EmptyState untuk menghandle apabila favItems kosong. Component tersebut menerima props.title yang akan menampilkan suatu tulisan apabila menu favorit belum terpilih.
Screenshots:
https://drive.google.com/open?id=1wyltNbGFAD6b8SD8qY5PloGOKoImFmKG
https://drive.google.com/open?id=1fxxse4Hugcf_GhpkmHOEW00ySDTwJOIF

---
## Tutorial 9
### What I have learned today

1. Ceritakan langkah - langkah yang kalian lakukan untuk solve LATIHAN no.1, dan mengapa kalian melakukan langkah - langkah tersebut?
  Saya membuat fungsi clearForm untuk data restoran yang memanggil fungsi setState agar tidak melakukan setState secara berulang kali. Lalu, saya memanggil fungsi tersebut pada fungsi addRestoranHandler karena fungsi tersebut merupakan event handler untuk memanggil onClick saat memunculkan form pada modal. Ketika button add restoran diklik, maka state dari restoran yang sebelumnya di-add akan kembali lagi ke default yaitu form kosong.

2. Jelaskan fungsi dari async dan await!
  Async: fungsi yang mengembalikan variabel promise (yang menghandle fungsi async) apabila tidak terdapat fungsi await. Apabila terdapat fungsi await, maka async akan langsung mengembalikan hasil method tersebut.
  Await: fungsi yang terdapat dalam fungsi async yang berfungsi untuk memberhentikan operasi fungsi async sementara, menunggu hasil promise, dan kemudian melanjutkan proses program.

3. Masukkan jawaban dari TODO (Screenshot) pada Component Lifecycle pada pertanyaan ini.
  Berikut hasil screenshot dari component lifecycle:
  https://ibb.co/db4Lv4K
  https://ibb.co/bXbyCgC
  https://ibb.co/kqPwFC1
  https://ibb.co/5TQPP3z
  https://ibb.co/rwfrY7j
  https://ibb.co/BwNBQ1g
  https://ibb.co/1Ls1Ptk
  https://ibb.co/swLjcKf

4. Jelaskan fungsi dari componentDidMount, shouldComponentUpdate, componentDidUpdate, componentWillReceiveProps, componentWillUnmount.
Notes : Penjelasan harus mencantumkan “kapan fungsi dipanggil” dan “use case apa saja yang biasanya menggunakan lifecycle method tersebut”.
- componentDidMount: method ini dipanggil setelah component di-mount ke dalam DOM dan biasanya digunakan apabila programmer menginginkan kode selalu dijalankan apabila component di-render. 
- shouldComponentUpdate: method ini selalu dipanggil sebelum render untuk mengatur apakah suatu render ulang perlu dilakukan atau tidak. Method ini biasanya digunakan untuk mengembalikan nilai pada kondisi tertentu. 
- componentDidUpdate: method ini dipanggil setelah dirender dan dapat digunakan untuk operasi DOM setelah data diperbaharui.
- componentWillReceiveProps: method ini dipanggil apabila props sudah berubah selain pada render pertama dan akan melakukan perubahan state berdasarkan props yang ada dengan yang baru.
- componentWillUnmount: method ini dipanggil ketika component sudah di-remove dari DOM dan digunakan ketika proses pembersihan dilakukan.
