import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

interface SewaBarang {
    double hitungDiskon(int hari);
    void tampilkanPromo();
}

abstract class Barang implements SewaBarang {
    protected String id;
    protected String nama;
    protected int stok;
    protected double harga;
    public static int totalBarang = 0;

    public Barang(String id, String nama, int stok, double harga) {
        this.id = id;
        setNama(nama);
        setStok(stok);
        setHarga(harga);
        totalBarang++;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public int getStok() { return stok; }
    public double getHarga() { return harga; }

    public void setNama(String nama) {
        if (nama == null || nama.isEmpty())
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        this.nama = nama;
    }

    public void setStok(int stok) {
        if (stok < 0)
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        this.stok = stok;
    }

    public void setHarga(double harga) {
        if (harga < 0)
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        this.harga = harga;
    }

    public void kurangiStok(int jumlah) {
        if (jumlah > stok)
            throw new IllegalArgumentException("Stok tidak mencukupi");
        stok -= jumlah;
    }

    public abstract double hitungHargaSewa(int hari);

    public static void tampilkanTotalBarang() {
        System.out.println("Total barang terdaftar: " + totalBarang);
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
        System.out.println("Stok: " + stok);
        System.out.println("Harga per Hari: Rp " + harga);
    }
}

class Tenda extends Barang {
    private int kapasitas;

    public Tenda(String id, String nama, int stok, double harga, int kapasitas) {
        super(id, nama, stok, harga);
        this.kapasitas = kapasitas;
    }

    @Override
    public double hitungHargaSewa(int hari) {
        return harga * hari;
    }

    @Override
    public double hitungDiskon(int hari) {
        return hari >= 5 ? hitungHargaSewa(hari) * 0.1 : 0;
    }

    @Override
    public void tampilkanPromo() {
        System.out.println("Tenda - Promo: Diskon 10% jika sewa lebih dari 5 hari.");
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Kapasitas: " + kapasitas + " orang");
    }
}

class PeralatanMasak extends Barang {
    private String bahan;

    public PeralatanMasak(String id, String nama, int stok, double harga, String bahan) {
        super(id, nama, stok, harga);
        this.bahan = bahan;
    }

    @Override
    public double hitungHargaSewa(int hari) {
        return harga * hari;
    }

    @Override
    public double hitungDiskon(int hari) {
        return hari >= 3 ? hitungHargaSewa(hari) * 0.05 : 0;
    }

    @Override
    public void tampilkanPromo() {
        System.out.println("Peralatan Masak - Promo: Diskon 5% jika sewa lebih dari 3 hari.");
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Bahan: " + bahan);
    }
}

class SleepingBag extends Barang {
    private double suhuMin;

    public SleepingBag(String id, String nama, int stok, double harga, double suhuMin) {
        super(id, nama, stok, harga);
        this.suhuMin = suhuMin;
    }

    @Override
    public double hitungHargaSewa(int hari) {
        return harga * hari;
    }

    @Override
    public double hitungDiskon(int hari) {
        return hari >= 4 ? hitungHargaSewa(hari) * 0.08 : 0;
    }

    @Override
    public void tampilkanPromo() {
        System.out.println("Sleeping Bag - Promo: Diskon 8% jika sewa lebih dari 4 hari.");
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Tahan Suhu Min: " + suhuMin + "°C");
    }
}

public class CampGear {
    private ArrayList<Barang> daftarBarang = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CampGear app = new CampGear();
        app.tampilkanMenu();
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
            System.out.println("7. Lihat Promo Barang");
            System.out.println("8. Lihat Total Barang");
            System.out.println("9. Keluar");
            System.out.print("Pilih menu: ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> tambahBarang();
                    case 2 -> lihatDaftarBarang();
                    case 3 -> lihatDetailBarang();
                    case 4 -> kurangiStokBarang();
                    case 5 -> updateBarang();
                    case 6 -> hapusBarang();
                    case 7 -> lihatPromo();
                    case 8 -> Barang.tampilkanTotalBarang();
                    case 9 -> {
                        System.out.println("Terima kasih telah menggunakan CampGear!");
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Masukkan angka yang valid!");
                scanner.nextLine();
            }
        }
    }

    private void tambahBarang() {
        try {
            System.out.print("Jenis Barang (1=Tenda, 2=Peralatan Masak, 3=Sleeping Bag): ");
            int jenis = scanner.nextInt();
            scanner.nextLine();

            System.out.print("ID Barang: ");
            String id = scanner.nextLine();

            System.out.print("Nama Barang: ");
            String nama = scanner.nextLine();

            System.out.print("Stok: ");
            int stok = scanner.nextInt();

            System.out.print("Harga per Hari: ");
            double harga = scanner.nextDouble();
            scanner.nextLine();

            switch (jenis) {
                case 1 -> {
                    System.out.print("Kapasitas: ");
                    int kapasitas = scanner.nextInt();
                    scanner.nextLine();
                    daftarBarang.add(new Tenda(id, nama, stok, harga, kapasitas));
                }
                case 2 -> {
                    System.out.print("Bahan: ");
                    String bahan = scanner.nextLine();
                    daftarBarang.add(new PeralatanMasak(id, nama, stok, harga, bahan));
                }
                case 3 -> {
                    System.out.print("Suhu Minimum (°C): ");
                    double suhu = scanner.nextDouble();
                    scanner.nextLine();
                    daftarBarang.add(new SleepingBag(id, nama, stok, harga, suhu));
                }
                default -> System.out.println("Jenis barang tidak dikenali.");
            }

            System.out.println("Barang berhasil ditambahkan.");
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void lihatDaftarBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang.");
        } else {
            System.out.println("=== Daftar Barang ===");
            for (Barang barang : daftarBarang) {
                System.out.println("- " + barang.getId() + " | " + barang.getNama());
            }
        }
    }

    private void lihatDetailBarang() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        Barang barang = cariBarang(id);
        if (barang != null) {
            barang.tampilkanInfo();
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    private void kurangiStokBarang() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        Barang barang = cariBarang(id);
        if (barang != null) {
            try {
                System.out.print("Jumlah yang ingin dikurangi: ");
                int jumlah = scanner.nextInt();
                scanner.nextLine();
                barang.kurangiStok(jumlah);
                System.out.println("Stok berhasil dikurangi.");
            } catch (Exception e) {
                System.out.println("Gagal: " + e.getMessage());
                scanner.nextLine();
            }
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    private void updateBarang() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        Barang barang = cariBarang(id);
        if (barang != null) {
            try {
                System.out.print("Nama baru: ");
                String nama = scanner.nextLine();
                System.out.print("Stok baru: ");
                int stok = scanner.nextInt();
                System.out.print("Harga baru: ");
                double harga = scanner.nextDouble();
                scanner.nextLine();

                barang.setNama(nama);
                barang.setStok(stok);
                barang.setHarga(harga);
                System.out.println("Barang berhasil diperbarui.");
            } catch (Exception e) {
                System.out.println("Update gagal: " + e.getMessage());
                scanner.nextLine();
            }
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    private void hapusBarang() {
        System.out.print("Masukkan ID Barang: ");
        String id = scanner.nextLine();
        Barang barang = cariBarang(id);
        if (barang != null) {
            daftarBarang.remove(barang);
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    private void lihatPromo() {
        for (Barang barang : daftarBarang) {
            barang.tampilkanPromo();
        }
    }

    private Barang cariBarang(String id) {
        for (Barang barang : daftarBarang) {
            if (barang.getId().equalsIgnoreCase(id)) {
                return barang;
            }
        }
        return null;
    }
}
