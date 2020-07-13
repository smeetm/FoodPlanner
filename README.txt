1. Install java --- add to PATH variable also add JAVA_HOME variable
2. Install maven -- add to PATH variable 
3. Update the file settings.xml in the maven directory

Eg: C:\Downloads\apache-maven-3.6.3-bin\apache-maven-3.6.3\conf\settings.xml

  <server>
   <id>azure-auth</id>
   	<configuration>
       		<client>28f0b580-7756-43dc-bcb9-d8e363ae595a</client>
       		<tenant>dcde968a-2d8a-452c-b752-f8edef2d9f72</tenant>
	   	<key>OTD1bL1-INe8DUuCS1~vFKEf87y7liPKZw</key>
       		<environment>AZURE</environment>
   	</configuration>
  </server>

3. Clone Repo https://github.com/smeetm/FoodPlanner.git
4. Test Changes on Local First: Run as Java Application , go to localhost:8080
5. TO Deploy Run: deploy.sh (For Mac), deploy.cmd(For Windows) or go to terminal/Command Prompt
   and run this command from the project root folder : mvn package azure-webapp:deploy
6. Go to https://foodplannerapp.azurewebsites.net/ to test changes
