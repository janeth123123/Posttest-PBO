import java.util.ArrayList;
import java.util.Scanner;

class Barang {
    private String id;
    private String nama;
    private int stok;
    private double harga;

    public Barang(String id, String nama, int stok, double harga) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    // Getter dan Setter
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
        this.nama = nama;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Stok: " + stok + ", Harga: Rp" + harga;
    }
}

class CampGearManager {
    private ArrayList<Barang> daftarBarang;
    private Scanner scanner; 
    public CampGearManager() {
        daftarBarang = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void tambahBarang() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Stok Barang: ");
        int stok = scanner.nextInt();
        System.out.print("Masukkan Harga Barang: ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); 
        Barang barang = new Barang(id, nama, stok, harga);
        daftarBarang.add(barang);
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

public class Main {
    public static void main(String[] args) {
        CampGearManager manager = new CampGearManager();
        manager.tampilkanMenu();
    }
}