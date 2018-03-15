import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
public class Taksod {
    public static void main(String[] sarvesaiake) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("takso.sis")));
        String esimeneRida = br.readLine();
        String teineRida = br.readLine();
        String kolmasRida = br.readLine();
        br.close();
        double kilomeetreid = Double.parseDouble(esimeneRida.split(" ")[0]);
        double tunde = (Double.parseDouble(esimeneRida.split(" ")[1])) / 60;
        double taksoKiirus = Double.parseDouble(teineRida.split(" ")[0]);
        double taksoTariif = Double.parseDouble(teineRida.split(" ")[1]);
        double jalaKiirus = Double.parseDouble(kolmasRida);
        double maxKaugusJalgsi = jalaKiirus * tunde;
        double maxKaugusTaksoga = taksoKiirus * tunde;
        if (maxKaugusJalgsi >= kilomeetreid) {
            v2ljatrykk("0.00");
        }
        else if (maxKaugusTaksoga < kilomeetreid) {
            v2ljatrykk("EI SAA");
        }
        else if (maxKaugusTaksoga == kilomeetreid) {
            v2ljatrykk(Double.toString(kilomeetreid * taksoTariif) + "0");
        }
        else {
            double D = taksoKiirus - jalaKiirus;
            double Dx = kilomeetreid - jalaKiirus * tunde;
            double x = Dx/D;
            double rahaKokku = taksoKiirus * x * taksoTariif;
            DecimalFormat df = new DecimalFormat("##.00");
            v2ljatrykk(df.format(rahaKokku).replace(",", "."));
        }
    }
    public static void v2ljatrykk(String s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("takso.val")));
        bw.write(s);
        bw.close();
    }
}