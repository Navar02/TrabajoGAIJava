package italia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import com.saxonica.xqj.SaxonXQDataSource;

public class ItaliaHTML {
	   public static void main(String[] args) {
	        String fileName = "output/italia.html";
	        
	        // create the data source and expression to process
	        try {

	            XQDataSource xqs = new SaxonXQDataSource();
	            XQConnection conn = xqs.getConnection();
	            XQExpression xqe = conn.createExpression();
	            String xqueryString = "xquery version \"3.0\";\r\n"
	                    + "\r\n"
	                    + "declare namespace eras=\"http://www.erasmusG4-6.org\";\r\n"
	                    + "declare namespace ita=\"http://www.italiaeras.org\";\r\n"
	                    + "\r\n"
	                    + "let $alumnos := doc('input/italia.xml')//Alumno\r\n"
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
	                    + "                            <p>Name: {$alumno/ita:ExtPersonalInformation/Name/text()}</p>\r\n"
	                    + "                            <p>Gender: {$alumno/ita:ExtPersonalInformation/Gender/text()}</p>\r\n"
	                    + "                            <p>Email: {$alumno/ita:ExtPersonalInformation/Email/text()}</p>\r\n"
	                    + "							   <p>Nationality: {$alumno/ita:ExtPersonalInformation/Nationality/text()}</p>\r\n"
	                    + "            				   <p>PizzaPreference: {$alumno/ita:ExtPersonalInformation/ita:PizzaPreference/text()}</p>\r\n"
	                    + "            				   <p>GelatoPreference: {$alumno/ita:ExtPersonalInformation/ita:GelatoPreference/text()}</p>\r\n"
	                    + "                        </div>\r\n"
	                    + "                        \r\n"
	                    + "                        <div class=\"section\">\r\n"
	                    + "                            <h2>Origin University</h2>\r\n"
	                    + "                            <p>Degree: {$alumno/OriginUniversity/Degree/text()}</p>\r\n"
	                    + "                            <p>Institution: {$alumno/OriginUniversity/Institution/text()}</p>\r\n"
	                    + "                            <p>Credits Achieved: {$alumno/OriginUniversity/CreditsAchieved/text()}</p>\r\n"
	                    + "                        </div>\r\n"
	                    + "                        \r\n"
	                    + "                        <div class=\"section\">\r\n"
	                    + "                            <h2>Erasmus Destination</h2>\r\n"
	                    + "                            <p>Degree: {$alumno/ita:ITA_EducationTypeDestination/Degree/text()}</p>\r\n"
	                    + "                            <p>Institution: {$alumno/ita:ITA_EducationTypeDestination/Institution/text()}</p>\r\n"
	                    + "                            <p>Start Date: {$alumno/ita:ITA_EducationTypeDestination/StartDate/text()}</p>\r\n"
	                    + "                            <p>End Date: {$alumno/ita:ITA_EducationTypeDestination/EndDate/text()}</p>\r\n"
	                    + "                            <p>Number of Subjects: {$alumno/ita:ITA_EducationTypeDestination/numberOfSubjects/text()}</p>\r\n"
	                    + "                            <h3>Subjects:</h3>\r\n"
	                    + "                            {\r\n"
	                    + "                                for $subject in $alumno/ita:ITA_EducationTypeDestination/Subject\r\n"
	                    + "                                return\r\n"
	                    + "                                    <div class=\"section\">\r\n"
	                    + "                                        <p>Name: {$subject/Name/text()}</p>\r\n"
	                    + "                                        <p>Credits: {$subject/Credits/text()}</p>\r\n"
	                    + "                                        <p>Description: {$subject/Description/text()}</p>\r\n"
	                    + "                                        <p>Specialization: {$subject/Specialization/text()}</p>\r\n"
	                    + "                                    </div>\r\n"
	                    + "                            }\r\n"
	                    + "							   <div><p>Region: {$alumno//ita:Region/text()}</p></div>\r\n"
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
