package espania;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.xquery.*;

import com.saxonica.xqj.*;

public class EspaniaHtml {

    public static void main(String[] args) {
        String fileName = "output/espania.html";
        
        // create the data source and expression to process
        try {

            XQDataSource xqs = new SaxonXQDataSource();
            XQConnection conn = xqs.getConnection();
            XQExpression xqe = conn.createExpression();
            String xqueryString = "xquery version \"3.0\";\r\n"
                    + "\r\n"
                    + "declare namespace eras=\"http://www.erasmusG4-6.org\";\r\n"
                    + "declare namespace espania=\"http://www.erasmusG4-6/espania.org\";\r\n"
                    + "\r\n"
                    + "let $alumnos := doc('input/Espania.xml')//Alumno\r\n"
                    + "\r\n"
                    + "return\r\n"
                    + "    <html>\r\n"
                    + "        <head>\r\n"
                    + "            <title>Erasmus Students</title>\r\n"
                    + "            <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\"/>\r\n"
                    + "        </head>\r\n"
                    + "        <body>\r\n"
                    + "            {\r\n"
                    + "                for $alumno in $alumnos\r\n"
                    + "                return\r\n"
                    + "                    <div class=\"alumno\">\r\n"
                    + "                        <div class=\"section\">\r\n"
                    + "                            <h2>Personal Information</h2>\r\n"
                    + "                            <p>Name: {$alumno/PersonalInformation/Name/text()}</p>\r\n"
                    + "                            <p>Gender: {$alumno/PersonalInformation/Gender/text()}</p>\r\n"
                    + "                            <p>Email: {$alumno/PersonalInformation/Email/text()}</p>\r\n"
                    + "                            <p>Nationality: {$alumno/PersonalInformation/Nationality/text()}</p>\r\n"
                    + "                            <p>DNI: {$alumno/PersonalInformation/DNI/text()}</p>\r\n"
                    + "                            <p>Direccion: {$alumno/PersonalInformation/Direccion/text()}</p>\r\n"
                    + "                            <p>Ciudad: {$alumno/PersonalInformation/Ciudad/text()}</p>\r\n"
                    + "                            <p>Codigo Postal: {$alumno/PersonalInformation/CodigoPostal/text()}</p>\r\n"
                    + "                            <p>Comunidad Autonoma: {$alumno/PersonalInformation/ComunidadAutonoma/text()}</p>\r\n"
                    + "                        </div>\r\n"
                    + "                        \r\n"
                    + "                        <div class=\"section\">\r\n"
                    + "                            <h2>Origin University</h2>\r\n"
                    + "                            <p>Degree: {$alumno/OriginUniversity/Degree/text()}</p>\r\n"
                    + "                            <p>Institution: {$alumno/OriginUniversity/Institution/text()}</p>\r\n"
                    + "                            <p>Credits Achieved: {$alumno/OriginUniversity/CreditsAchieved/text()}</p>\r\n"
                    + "                            <p>Campus: {$alumno/OriginUniversity/Campus/text()}</p>\r\n"
                    + "                            <p>Facultad: {$alumno/OriginUniversity/Facultad/text()}</p>\r\n"
                    + "                            <p>Departamento: {$alumno/OriginUniversity/Departamento/text()}</p>\r\n"
                    + "                        </div>\r\n"
                    + "                        \r\n"
                    + "                        <div class=\"section\">\r\n"
                    + "                            <h2>Erasmus Destination</h2>\r\n"
                    + "                            <p>Degree: {$alumno/ErasmusDestination/Degree/text()}</p>\r\n"
                    + "                            <p>Institution: {$alumno/ErasmusDestination/Institution/text()}</p>\r\n"
                    + "                            <p>Start Date: {$alumno/ErasmusDestination/StartDate/text()}</p>\r\n"
                    + "                            <p>End Date: {$alumno/ErasmusDestination/EndDate/text()}</p>\r\n"
                    + "                            <p>Number of Subjects: {$alumno/ErasmusDestination/numberOfSubjects/text()}</p>\r\n"
                    + "                            <h3>Subjects:</h3>\r\n"
                    + "                            {\r\n"
                    + "                                for $subject in $alumno/ErasmusDestination/Subject\r\n"
                    + "                                return\r\n"
                    + "                                    <div class=\"section\">\r\n"
                    + "                                        <p>Name: {$subject/Name/text()}</p>\r\n"
                    + "                                        <p>Credits: {$subject/Credits/text()}</p>\r\n"
                    + "                                        <p>Description: {$subject/Description/text()}</p>\r\n"
                    + "                                        <p>Specialization: {$subject/Specialization/text()}</p>\r\n"
                    + "                                    </div>\r\n"
                    + "                            }\r\n"
                    + "                        </div>\r\n"
                    + "                        \r\n"
                    + "                        <div class=\"section\">\r\n"
                    + "                            <h2>Experience</h2>\r\n"
                    + "                            {\r\n"
                    + "                                for $experience in $alumno/Experience\r\n"
                    + "                                return\r\n"
                    + "                                    <div class=\"section\">\r\n"
                    + "                                        <p>Position: {$experience/Position/text()}</p>\r\n"
                    + "                                        <p>Employer: {$experience/Employer/text()}</p>\r\n"
                    + "                                        <p>Start Date: {$experience/StartDate/text()}</p>\r\n"
                    + "                                        <p>End Date: {$experience/EndDate/text()}</p>\r\n"
                    + "                                        <p>Empresa: {$experience/Empresa/text()}</p>\r\n"
                    + "                                        <p>Ciudad: {$experience/Ciudad/text()}</p>\r\n"
                    + "                                    </div>\r\n"
                    + "                            }\r\n"
                    + "                        </div>\r\n"
                    + "                    </div>\r\n"
                    + "            }\r\n"
                    + "        </body>\r\n"
                    + "    </html>\r\n"
                    + "";
            XQResultSequence rs = xqe.executeQuery(xqueryString);
            
            // Write the result to an HTML file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            while (rs.next()) {
                writer.write(rs.getItemAsString(null));
            }
            writer.close();
            
            System.out.println("HTML file generated successfully.");
            
            conn.close();
        }
        catch(IOException | XQException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
    }

}
