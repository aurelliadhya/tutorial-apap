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
## Tutorial 
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