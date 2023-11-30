package utilitaire;

import DAO.DAOCompose;
import DAO.DAOOrdonnance;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.text.BadElementException;
import gestion.Compose;
import principal.FrmAchat;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FilePDF {

    static DAOOrdonnance daoOrdonnance = new DAOOrdonnance();
    static DAOCompose daoCompose = new DAOCompose();
    private static String IMG = "src/Configuration/bank/Logo-removebg-preview.png";


    /*
        Génére un nouveau PDF vierge à l'emplacement cibler
     */
    /**
     *
     * @param name
     * @param date
     * @param id
     * @throws BadElementException
     * @throws IOException
     */
    public static void init(String name, String date, int id) throws BadElementException, IOException {

        String FILE = "src/utilitaire/PDF/" + name + "-" + date + ".pdf";

        File file = new File(FILE);
        file.getParentFile().mkdirs();

        new FilePDF().manipulatePdf(FILE, id);
    }

    /*
        Manipule le dernier PDF créer et le remplit des informations dont il fait appel
        ou du style qu'on lui impose
     */
    /**
     *
     * @param file
     * @param id
     */
    private static void manipulatePdf(String file, int id) {
        try {
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // Création d'un objet PdfWriter pour écrire dans un fichier PDF
            PdfWriter writer = new PdfWriter(file);

            // Création d'un objet PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // Création d'un objet Document
            Document document = new Document(pdf);

            // Style pour les titres
            Style titleStyle = new Style()
                    .setFont(font)
                    .setFontSize(18);

            // Style pour le texte principal
            Style normalStyle = new Style()
                    .setFont(font)
                    .setFontSize(12);

            String nomPatient = daoOrdonnance.find(id).getClient().getPersonne().getPrenom() +" "+ daoOrdonnance.find(id).getClient().getPersonne().getName();
            String dateNaissance = daoOrdonnance.find(id).getClient().getDate_Naissance();
            String medecin = "Dr " + daoOrdonnance.find(id).getMedecin().getPersonne().getPrenom() +" "+ daoOrdonnance.find(id).getMedecin().getPersonne().getName();
            String dateOrdonnance = daoOrdonnance.find(id).getOrd_Date();

            //LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Ajout de l'image en haut à droite
            Image image = new Image(ImageDataFactory.create(IMG));
            image.setWidth(50);
            image.setHeight(50);
            document.add(image.setHorizontalAlignment(HorizontalAlignment.RIGHT));

            // Ajout des détails de l'ordonnance au document
            document.add(new Paragraph("ORDONNANCE MÉDICALE").addStyle(titleStyle));
            document.add(new Paragraph("Patient : " + nomPatient).addStyle(normalStyle));
            document.add(new Paragraph("Date de naissance : " + dateNaissance).addStyle(normalStyle));
            document.add(new Paragraph("Médecin : " + medecin).addStyle(normalStyle));
            document.add(new Paragraph("Date de l'ordonnance : " + dateOrdonnance).addStyle(normalStyle));
            document.add(new Paragraph("\n"));

            // Ajout des prescriptions médicales
            document.add(new Paragraph("Prescriptions :").addStyle(titleStyle).setMarginBottom(10));
            for (Compose m : daoCompose.findwhere(id)) {
                document.add(createPrescription(m.getMedicament().getNom(), m.getCompose_Qte(), m.getMedicament().getCategorie().getCat_Categorie()));
            }

            // Ajout de la signature de la pharmacie en bas de la page
            Paragraph signature = new Paragraph("La pharmacie Sparadrap vous remercie de votre passage !");
            signature.setTextAlignment(TextAlignment.CENTER);
            signature.setFixedPosition(50,50,500);
            document.add(signature);

            // Fermeture du document
            document.close();

            System.out.println("L'ordonnance a été générée avec succès !");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Méthode pour créer une section de prescription
     */
    /**
     *
     * @param nomMedicament
     * @param posologie
     * @param instructions
     * @return
     * @throws IOException
     */
    private static Div createPrescription(String nomMedicament, int posologie, String instructions) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        Style prescriptionStyle = new Style()
                .setFont(font)
                .setFontSize(12);

        Paragraph medication = new Paragraph(nomMedicament).addStyle(prescriptionStyle);
        medication.setMarginBottom(5);

        Paragraph dosage = new Paragraph("Quantité : " + posologie).addStyle(prescriptionStyle);
        dosage.setMarginBottom(3);

        Paragraph instruct = new Paragraph("Catégorie : " + instructions).addStyle(prescriptionStyle);

        Div prescriptionDiv = new Div();
        prescriptionDiv.setBorder(new SolidBorder(1));
        prescriptionDiv.add(medication);
        prescriptionDiv.add(instruct);
        prescriptionDiv.add(dosage);
        prescriptionDiv.setMarginBottom(15);

        return prescriptionDiv;
    }
}