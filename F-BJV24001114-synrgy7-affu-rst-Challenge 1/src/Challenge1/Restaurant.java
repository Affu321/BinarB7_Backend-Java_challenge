import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean inginPesanLagi = true;
        double totalHarga = 0;
        ArrayList<String> pesanan = new ArrayList<>();
        ArrayList<Integer> kuantitas = new ArrayList<>();
        int totalItems = 0;

        System.out.println("==========================");
        System.out.println("Selamat Datang di BinarFud");
        System.out.println("==========================");

        while (inginPesanLagi) {
            System.out.println("Silahkan Pilih Makanan :");
            System.out.println("1. Nasi Goreng  | Rp. 15.000");
            System.out.println("2. Mie Goreng   | Rp. 13.000");
            System.out.println("3. Nasi + ayam  | Rp. 18.000");
            System.out.println("4. Es Teh       | Rp. 3.000");
            System.out.println("5. Es Jeruk     | Rp. 5.000");
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("=>");
            int pilih = input.nextInt();

            if (pilih == 0) {
                System.out.println("Thankyou");
                break;
            }

            double harga = 0;
            String namaMenu = "";
            switch (pilih) {
                case 1:
                    harga = 15000.0;
                    namaMenu = "Nasi Goreng";
                    break;
                case 2:
                    harga = 13000.0;
                    namaMenu = "Mie Goreng";
                    break;
                case 3:
                    harga = 18000.0;
                    namaMenu = "Nasi + Ayam";
                    break;
                case 4:
                    harga = 3000.0;
                    namaMenu = "Es Teh";
                    break;
                case 5:
                    harga = 5000.0;
                    namaMenu = "Es Jeruk";
                    break;
                case 99:
                    System.out.println("Pesan dan Bayar");
                    inginPesanLagi = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
            if (pilih != 0 && pilih != 99) {
                System.out.println("====================");
                System.out.println("Berapa pesanan anda");
                System.out.println("====================");
                System.out.println(namaMenu + " | Harga: Rp." + harga);
                System.out.println("(Input 0 untuk Kembali)");
                System.out.print("qty=>");
                int qty = input.nextInt();
                pesanan.add(namaMenu);
                kuantitas.add(qty);
                totalHarga += harga * qty;
                totalItems += qty;
            }
        }
        if (totalItems > 0) {
            System.out.println("========================");
            System.out.println("Konfirmasi & Pembayaran");
            System.out.println("========================");
            System.out.println("Pesanan Anda:");
            for (int i = 0; i < pesanan.size(); i++) {
                String menu = pesanan.get(i);
                int qty = kuantitas.get(i);
                double hargaMenu = 0;
                switch (menu) {
                    case "Nasi Goreng":
                        hargaMenu = 15000.0;
                        break;
                    case "Mie Goreng":
                        hargaMenu = 13000.0;
                        break;
                    case "Nasi + Ayam":
                        hargaMenu = 18000.0;
                        break;
                    case "Es Teh":
                        hargaMenu = 3000.0;
                        break;
                    case "Es Jeruk":
                        hargaMenu = 5000.0;
                        break;
                }
                double subtotal = hargaMenu * qty;
                System.out.println(menu + " | Qty: " + qty + " | Total: Rp." + subtotal);
            }
            System.out.println("Total Harga: Rp." + totalHarga);
            System.out.println("========================");
            System.out.println("Menu Pembayaran:");
            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke Menu Utama");
            System.out.println("0. Keluar Aplikasi");

            int pilihanPembayaran = input.nextInt();
            switch (pilihanPembayaran) {
                case 1:
                    try {
                        FileWriter myWriter = new FileWriter("struk_pembelian.txt");
                        myWriter.write("========================\n");
                        myWriter.write("Struk Pembelian:\n");
                        for (int i = 0; i < pesanan.size(); i++) {
                            String menu = pesanan.get(i);
                            int qty = kuantitas.get(i);
                            double hargaMenu = 0;
                            switch (menu) {
                                case "Nasi Goreng":
                                    hargaMenu = 15000.0;
                                    break;
                                case "Mie Goreng":
                                    hargaMenu = 13000.0;
                                    break;
                                case "Nasi + Ayam":
                                    hargaMenu = 18000.0;
                                    break;
                                case "Es Teh":
                                    hargaMenu = 3000.0;
                                    break;
                                case "Es Jeruk":
                                    hargaMenu = 5000.0;
                                    break;
                            }
                            double subtotal = hargaMenu * qty;
                            myWriter.write(menu + " | Qty: " + qty + " | Total: Rp." + subtotal + "\n");
                            System.out.println(menu + " | Qty: " + qty + " | Total: Rp." + subtotal); // Menampilkan isi struk di output
                        }
                        myWriter.write("Total Harga: Rp." + totalHarga + "\n");
                        myWriter.write("Terima kasih telah menggunakan layanan kami.\n");
                        myWriter.close();
                        System.out.println("Struk pembelian telah disimpan dalam file 'struk_pembelian.txt'");
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan dalam menyimpan struk pembelian.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } else {
            System.out.println("Tidak ada pesanan yang diproses.");
        }
    }
}
