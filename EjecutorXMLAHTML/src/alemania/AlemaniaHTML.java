package alemania;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.xquery.*;

import com.saxonica.xqj.*;

public class AlemaniaHTML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "output/alemania.html";

		try {
			XQDataSource xqs = new SaxonXQDataSource();
			XQConnection conn = xqs.getConnection();
			XQExpression xqe = conn.createExpression();
			String xqueryString = "xquery version \"3.0\";\r\n" + "\r\n"
					+ "declare namespace eras=\"http://www.erasmusG4-6.org\";\r\n"
					+ "declare namespace espania=\"http://www.erasmusG4-6/alemania.org\";\r\n" + "\r\n"
					+ "let $alumnos := doc('input/alemania.xml')//Alumno\r\n" + "\r\n" + "return\r\n" + "    <html>\r\n"
					+ "        <head>\r\n" + "            <title>Erasmus Students</title>\r\n"
					+ "            <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\"/>\r\n"
					+ "        </head>\r\n" + "        <body>\r\n" + "            {\r\n"
					+ "                for $alumno in $alumnos\r\n" + "                return\r\n"
					+ "                    <div class=\"alumno\">\r\n"
					+ "                        <div class=\"section\">\r\n"
					+ "                            <h2>Personal Information</h2>\r\n"
					+ "                            <p>Name: {$alumno/PersonalInformation/Name/text()}</p>\r\n"
					+ "                            <p>Gender: {$alumno/PersonalInformation/Gender/text()}</p>\r\n"
					+ "                            <p>Email: {$alumno/PersonalInformation/Email/text()}</p>\r\n"
					+ "                            <p>Nationality: {$alumno/PersonalInformation/Nationality/text()}</p>\r\n"
					+ "                            <p>DIA: {$alumno/PersonalInformation/DIA/text()}</p>\r\n"
					+ "                            <p>BirthDate: {$alumno/PersonalInformation/BirthDate/text()}</p>\r\n"
					+ "                            <p>Estado: {$alumno/PersonalInformation/Estado/text()}</p>\r\n"
					+ "                        </div>\r\n" + "                        \r\n"
					+ "                        <div class=\"section\">\r\n"
					+ "                            <h2>Origin University</h2>\r\n"
					+ "                            <p>Degree: {$alumno/OriginUniversity/Degree/text()}</p>\r\n"
					+ "                            <p>Institution: {$alumno/OriginUniversity/Institution/text()}</p>\r\n"
					+ "                            <p>Credits Achieved: {$alumno/OriginUniversity/CreditsAchieved/text()}</p>\r\n"
					+ "                            <p>GradeType: {$alumno/OriginUniversity/GradeType/text()}</p>\r\n"
					+ "                            <p>Extra: {$alumno/OriginUniversity/Extra/text()}</p>\r\n"
					+ "                        </div>\r\n" + "                        \r\n"
					+ "                        <div class=\"section\">\r\n"
					+ "                            <h2>Erasmus Destination</h2>\r\n"
					+ "                            <p>Degree: {$alumno/ErasmusDestination/Degree/text()}</p>\r\n"
					+ "                            <p>Institution: {$alumno/ErasmusDestination/Institution/text()}</p>\r\n"
					+ "                            <p>Start Date: {$alumno/ErasmusDestination/StartDate/text()}</p>\r\n"
					+ "                            <p>End Date: {$alumno/ErasmusDestination/EndDate/text()}</p>\r\n"
					+ "                            <p>Number of Subjects: {$alumno/ErasmusDestination/numberOfSubjects/text()}</p>\r\n"
					+ "                            <h3>Subjects:</h3>\r\n" + "                            {\r\n"
					+ "                                for $subject in $alumno/ErasmusDestination/Subject\r\n"
					+ "                                return\r\n"
					+ "                                    <div class=\"section\">\r\n"
					+ "                                        <p>Name: {$subject/Name/text()}</p>\r\n"
					+ "                                        <p>Credits: {$subject/Credits/text()}</p>\r\n"
					+ "                                        <p>Description: {$subject/Description/text()}</p>\r\n"
					+ "                                        <p>Specialization: {$subject/Specialization/text()}</p>\r\n"
					+ "                                    </div>\r\n" + "                            }\r\n"
					+ "                        </div>\r\n" + "                        \r\n"
					+ "                        <div class=\"section\">\r\n"
					+ "                            <h2>Experience</h2>\r\n" + "                            {\r\n"
					+ "                                for $experience in $alumno/Experience\r\n"
					+ "                                return\r\n"
					+ "                                    <div class=\"section\">\r\n"
					+ "                                        <p>Position: {$experience/Position/text()}</p>\r\n"
					+ "                                        <p>Employer: {$experience/Employer/text()}</p>\r\n"
					+ "                                        <p>Start Date: {$experience/StartDate/text()}</p>\r\n"
					+ "                                        <p>End Date: {$experience/EndDate/text()}</p>\r\n"
					+ "                                        <p>Empresa: {$experience/Empresa/text()}</p>\r\n"
					+ "                                        <p>Ciudad: {$experience/Ciudad/text()}</p>\r\n"
					+ "                                    </div>\r\n" + "                            }\r\n"
					+ "                        </div>\r\n" + "                    </div>\r\n" + "            }\r\n"
					+ "        </body>\r\n" + "    </html>\r\n" + "";

			XQResultSequence rs = xqe.executeQuery(xqueryString);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

			while (rs.next()) {
				String aline = rs.getItemAsString(null);
				bufferedWriter.write(aline);
			}
			System.out.println("HTML file generated successfully.");

			conn.close();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erroe generating HTML.");
		} catch (Exception e) {
			System.out.println("Erroe generating HTML.");
		}
	}

}
