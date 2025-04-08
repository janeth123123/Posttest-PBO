import java.util.ArrayList;
import java.util.Scanner;

public class jun {

    // Class Barang untuk merepresentasikan item alat kemping
    static class Barang {
        protected String id;
        private String nama;
        private int stok;
        private double harga;

        public Barang(String id, String nama, int stok, double harga) {
            this.id = id;
            setNama(nama);
            setStok(stok);
            setHarga(harga);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public int getKapasitas() {
            return kapasitas;
        }

        public void setKapasitas(int kapasitas) {
            if (kapasitas > 0) {
                this.kapasitas = kapasitas;
            } else {
                System.out.println("Kapasitas harus lebih dari 0.");
            }
        }

        @Override
        public String toString() {
            return super.toString() + ", Kapasitas: " + kapasitas + " orang (Tenda)";
        }
    }

    // Subclass PeralatanMasak
    static class PeralatanMasak extends Barang {
        private String bahan;

        public PeralatanMasak(String id, String nama, int stok, double harga, String bahan) {
            super(id, nama, stok, harga);
            this.bahan = bahan;
        }

        public String getBahan() {
            return bahan;
        }

        public void setBahan(String bahan) {
            if (bahan != null && !bahan.trim().isEmpty()) {
                this.bahan = bahan;
            } else {
                System.out.println("Bahan tidak boleh kosong.");
            }
        }

        @Override
        public String toString() {
            return super.toString() + ", Bahan: " + bahan + " (Peralatan Masak)";
        }
    }

    // Class CampGearManager
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
                System.out.println("2. Lihat Barang");
                System.out.println("3. Update Barang");
                System.out.println("4. Hapus Barang");
                System.out.println("5. Keluar");
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
                        updateBarang();
                        break;
                    case 4:
                        hapusBarang();
                        break;
                    case 5:
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
