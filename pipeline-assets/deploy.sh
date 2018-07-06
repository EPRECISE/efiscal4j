echo "<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd\"> \
  <servers> \
	<server> \
      	<id>eprecise-nexus</id> \
      	<username>${MAVEN_REPO_USERNAME}</username> \
      	<password>${MAVEN_REPO_PASSWORD}</password> \
    </server> \
  </servers> \
 </settings> \
      " | tee $HOME/.m2/settings.xml > /dev/null
       
        
mvn deploy -DskipTests