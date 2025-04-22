import java.util.ArrayList;
import java.util.Scanner;

public final class jun {  // Made the main class final

    // Abstract Superclass Barang
    abstract static class Barang {
        protected final String id;  // Made id attribute final
        private String nama;
        private int stok;
        private double harga;

        public Barang(String id, String nama, int stok, double harga) {
            this.id = id;
            setNama(nama);
            setStok(stok);
            setHarga(harga);
        }

        public final String getId() {  // Made this method final
            return id;
        }

        // Abstract method
        public abstract String getJenis();

        public void setId(String id) {
            // Can't modify id because it's final
            System.out.println("ID cannot be changed as it's final");
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            if (nama != null && !nama.trim().isEmpty()) {
                this.nama = nama;
            } else {
                System.out.println("Nama barang tidak boleh kosong!");
            }
        }

        public int getStok() {
            return stok;
        }

        public void setStok(int stok) {
            if (stok >= 0) {
                this.stok = stok;
            } else {
                System.out.println("Stok tidak boleh negatif!");
            }
        }

        public double getHarga() {
            return harga;
        }

        public void setHarga(double harga) {
            if (harga >= 0) {
                this.harga = harga;
            } else {
                System.out.println("Harga tidak boleh negatif!");
            }
        }

        // Method overloading untuk mengurangi stok (Static Polymorphism)
        // Metode 1: Mengurangi stok dengan jumlah tertentu
        public boolean kurangiStok(int jumlah) {
            if (jumlah <= 0) {
                System.out.println("Jumlah pengurangan harus positif!");
                return false;
            }
            
            if (stok >= jumlah) {
                stok -= jumlah;
                return true;
            } else {
                System.out.println("Stok tidak mencukupi!");
                return false;
            }
        }
        
        // Metode 2: Mengurangi stok dengan jumlah tertentu dan memberikan alasan
        public boolean kurangiStok(int jumlah, String alasan) {
            if (kurangiStok(jumlah)) {
                System.out.println("Stok berkurang karena: " + alasan);
                return true;
            }
            return false;
        }

        // Method yang akan di-override oleh subclass
        public void tampilkanDetail() {
            System.out.println("Detail Barang:");
            System.out.println("ID: " + id);
            System.out.println("Nama: " + nama);
            System.out.println("Stok: " + stok);
            System.out.println("Harga: Rp" + harga);
        }
        
        // Method untuk menghitung harga sewa
        public double hitungHargaSewa(int hari) {
            return harga * hari;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Nama: " + nama + ", Stok: " + stok + ", Harga: Rp" + harga;
        }
    }

    // Subclass Tenda
    static class Tenda extends Barang {
        private int kapasitas;

        public Tenda(String id, String nama, int stok, double harga, int kapasitas) {
            super(id, nama, stok, harga);
            this.kapasitas = kapasitas;
        }

        @Override
        public String getJenis() {
            return "Tenda";
        }

        public int getKapasitas() {
            return kapasitas;
        }

        public void setKapasitas(int kapasitas) {
            this.kapasitas = kapasitas;
        }
        
        // Override method tampilkanDetail
        @Override
        public void tampilkanDetail() {
            super.tampilkanDetail();
            System.out.println("Kapasitas: " + kapasitas + " orang");
            System.out.println("Jenis: " + getJenis());
        }
        
        // Override method hitungHargaSewa - Tenda memiliki biaya tambahan untuk kapasitas besar
        @Override
        public double hitungHargaSewa(int hari) {
            double hargaDasar = super.hitungHargaSewa(hari);
            // Tambahan biaya 10% untuk tenda dengan kapasitas > 4 orang
            if (kapasitas > 4) {
                return hargaDasar * 1.1;
            }
            return hargaDasar;
        }

        @Override
        public String toString() {
            return super.toString() + ", Kapasitas: " + kapasitas + " orang (" + getJenis() + ")";
        }
    }

    // Subclass PeralatanMasak
    static class PeralatanMasak extends Barang {
        private String bahan;

        public PeralatanMasak(String id, String nama, int stok, double harga, String bahan) {
            super(id, nama, stok, harga);
            this.bahan = bahan;
        }

        @Override
        public String getJenis() {
            return "Peralatan Masak";
        }

        public String getBahan() {
            return bahan;
        }

        public void setBahan(String bahan) {
            this.bahan = bahan;
        }
        
        // Override method tampilkanDetail
        @Override
        public void tampilkanDetail() {
            super.tampilkanDetail();
            System.out.println("Bahan: " + bahan);
            System.out.println("Jenis: " + getJenis());
        }
        
        // Override method hitungHargaSewa - Peralatan stainless steel lebih mahal
        @Override
        public double hitungHargaSewa(int hari) {
            double hargaDasar = super.hitungHargaSewa(hari);
            // Tambahan biaya 15% untuk peralatan berbahan stainless steel
            if (bahan.toLowerCase().contains("stainless")) {
                return hargaDasar * 1.15;
            }
            return hargaDasar;
        }

        @Override
        public String toString() {
            return super.toString() + ", Bahan: " + bahan + " (" + getJenis() + ")";
        }
    }

    // Subclass SleepingBag
    static class SleepingBag extends Barang {
        private int suhuMin;

        public SleepingBag(String id, String nama, int stok, double harga, int suhuMin) {
            super(id, nama, stok, harga);
            this.suhuMin = suhuMin;
        }

        @Override
        public String getJenis() {
            return "Sleeping Bag";
        }

        public int getSuhuMin() {
            return suhuMin;
        }

        public void setSuhuMin(int suhuMin) {
            this.suhuMin = suhuMin;
        }
        
        // Override method tampilkanDetail
        @Override
        public void tampilkanDetail() {
            super.tampilkanDetail();
            System.out.println("Suhu Minimum: " + suhuMin + "°C");
            System.out.println("Jenis: " + getJenis());
        }
        
        // Override method hitungHargaSewa - Sleeping bag untuk suhu ekstrem lebih mahal
        @Override
        public double hitungHargaSewa(int hari) {
            double hargaDasar = super.hitungHargaSewa(hari);
            // Tambahan biaya 20% untuk sleeping bag dengan suhu minimum < -10°C
            if (suhuMin < -10) {
                return hargaDasar * 1.2;
            }
            return hargaDasar;
        }

        @Override
        public String toString() {
            return super.toString() + ", Suhu Minimum: " + suhuMin + "°C (" + getJenis() + ")";
        }
    }

    // CampGearManager dengan CRUD
    static class CampGearManager {
        private ArrayList<Barang> daftarBarang;
        private Scanner scanner;

        public CampGearManager() {
            daftarBarang = new ArrayList<>();
            scanner = new Scanner(System.in);
        }

        public void tambahBarang() {
            System.out.println("Pilih jenis barang:");
            System.out.println("1. Tenda");
            System.out.println("2. Peralatan Masak");
            System.out.println("3. Sleeping Bag");
            System.out.print("Pilihan: ");
            int jenis = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Masukkan ID Barang: ");
            String id = scanner.nextLine();
            System.out.print("Masukkan Nama Barang: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan Stok Barang: ");
            int stok = scanner.nextInt();
            System.out.print("Masukkan Harga Barang: ");
            double harga = scanner.nextDouble();
            scanner.nextLine();

            if (jenis == 1) {
                System.out.print("Masukkan Kapasitas Tenda: ");
                int kapasitas = scanner.nextInt();
                scanner.nextLine();
                Tenda tenda = new Tenda(id, nama, stok, harga, kapasitas);
                daftarBarang.add(tenda);
            } else if (jenis == 2) {
                System.out.print("Masukkan Bahan Peralatan Masak: ");
                String bahan = scanner.nextLine();
                PeralatanMasak alatMasak = new PeralatanMasak(id, nama, stok, harga, bahan);
                daftarBarang.add(alatMasak);
            } else if (jenis == 3) {
                System.out.print("Masukkan Suhu Minimum Sleeping Bag (°C): ");
                int suhuMin = scanner.nextInt();
                scanner.nextLine();
                SleepingBag sb = new SleepingBag(id, nama, stok, harga, suhuMin);
                daftarBarang.add(sb);
            } else {
                System.out.println("Jenis barang tidak valid!");
                return;
            }

            System.out.println("Barang berhasil ditambahkan!");
        }

        public void lihatBarang() {
            if (daftarBarang.isEmpty()) {
                System.out.println("Tidak ada barang yang tersedia.");
            } else {
                System.out.println("\nDaftar Barang:");
                for (Barang barang : daftarBarang) {
                    System.out.println(barang);
                }
            }
        }

        public void lihatDetailBarang() {
            if (daftarBarang.isEmpty()) {
                System.out.println("Tidak ada barang yang tersedia.");
                return;
            }
            
            System.out.print("Masukkan ID Barang untuk melihat detail: ");
            String id = scanner.nextLine();
            
            boolean found = false;
            for (Barang barang : daftarBarang) {
                if (barang.getId().equals(id)) {
                    // Memanggil method polymorphic - tampilkanDetail()
                    // Di sini terjadi dynamic binding berdasarkan tipe objek saat runtime
                    barang.tampilkanDetail();
                    found = true;
                    
                    // Menampilkan contoh harga sewa
                    System.out.println("\nContoh Harga Sewa:");
                    System.out.println("Harga sewa 3 hari: Rp" + barang.hitungHargaSewa(3));
                    System.out.println("Harga sewa 7 hari: Rp" + barang.hitungHargaSewa(7));
                    break;
                }
            }
            
            if (!found) {
                System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            }
        }

        public void kurangiStokBarang() {
            if (daftarBarang.isEmpty()) {
                System.out.println("Tidak ada barang yang tersedia.");
                return;
            }
            
            System.out.print("Masukkan ID Barang yang ingin dikurangi stoknya: ");
            String id = scanner.nextLine();
            
            Barang barangTarget = null;
            for (Barang barang : daftarBarang) {
                if (barang.getId().equals(id)) {
                    barangTarget = barang;
                    break;
                }
            }
            
            if (barangTarget != null) {
                System.out.print("Masukkan jumlah pengurangan stok: ");
                int jumlah = scanner.nextInt();
                scanner.nextLine();
                
                System.out.println("Apakah ada alasan untuk pengurangan stok? (Y/N): ");
                String jawaban = scanner.nextLine();
                
                if (jawaban.equalsIgnoreCase("Y")) {
                    System.out.print("Masukkan alasan: ");
                    String alasan = scanner.nextLine();
                    // Memanggil method overloading dengan 2 parameter
                    barangTarget.kurangiStok(jumlah, alasan);
                } else {
                    // Memanggil method overloading dengan 1 parameter
                    barangTarget.kurangiStok(jumlah);
                }
            } else {
                System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            }
        }

        public void updateBarang() {
            System.out.print("Masukkan ID Barang yang ingin diupdate: ");
            String id = scanner.nextLine();
            Barang barangToUpdate = null;

            for (Barang barang : daftarBarang) {
                if (barang.getId().equals(id)) {
                    barangToUpdate = barang;
                    break;
                }
            }

            if (barangToUpdate != null) {
                System.out.print("Masukkan Nama Barang Baru: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan Stok Barang Baru: ");
                int stok = scanner.nextInt();
                System.out.print("Masukkan Harga Barang Baru: ");
                double harga = scanner.nextDouble();
                scanner.nextLine();

                barangToUpdate.setNama(nama);
                barangToUpdate.setStok(stok);
                barangToUpdate.setHarga(harga);

                if (barangToUpdate instanceof Tenda) {
                    System.out.print("Masukkan Kapasitas Baru: ");
                    int kapasitas = scanner.nextInt();
                    scanner.nextLine();
                    ((Tenda) barangToUpdate).setKapasitas(kapasitas);
                } else if (barangToUpdate instanceof PeralatanMasak) {
                    System.out.print("Masukkan Bahan Baru: ");
                    String bahan = scanner.nextLine();
                    ((PeralatanMasak) barangToUpdate).setBahan(bahan);
                } else if (barangToUpdate instanceof SleepingBag) {
                    System.out.print("Masukkan Suhu Minimum Baru: ");
                    int suhuMin = scanner.nextInt();
                    scanner.nextLine();
                    ((SleepingBag) barangToUpdate).setSuhuMin(suhuMin);
                }

                System.out.println("Barang berhasil diupdate!");
            } else {
                System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            }
        }

        public void hapusBarang() {
            System.out.print("Masukkan ID Barang yang ingin dihapus: ");
            String id = scanner.nextLine();
            Barang barangToRemove = null;

            for (Barang barang : daftarBarang) {
                if (barang.getId().equals(id)) {
                    barangToRemove = barang;
                    break;
                }
            }

            if (barangToRemove != null) {
                daftarBarang.remove(barangToRemove);
                System.out.println("Barang berhasil dihapus!");
            } else {
                System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            }
        }

        public void tampilkanMenu() {
            while (true) {
                System.out.println("\n=== Menu CampGear ===");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Lihat Daftar Barang");
                System.out.println("3. Lihat Detail Barang");
                System.out.println("4. Kurangi Stok Barang");
                System.out.println("5. Update Barang");
                System.out.println("6. Hapus Barang");
                System.out.println("7. Keluar");
                System.out.print("Pilih menu: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        tambahBarang();
                        break;
                    case 2:
                        lihatBarang();
                        break;
                    case 3:
                        lihatDetailBarang();
                        break;
                    case 4:
                        kurangiStokBarang();
                        break;
                    case 5:
                        updateBarang();
                        break;
                    case 6:
                        hapusBarang();
                        break;
                    case 7:
                        System.out.println("Terima kasih telah menggunakan CampGear!");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            }
        }
    }

    public static void main(String[] args) {
        CampGearManager manager = new CampGearManager();
        manager.tampilkanMenu();
    }
}