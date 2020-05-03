# AWS Athena Example

Download Athena JDBC driver:
https://docs.aws.amazon.com/athena/latest/ug/connect-with-jdbc.html

Install driver to your local maven repo:
mvn install:install-file -Dfile=<path-to-folder>/AthenaJDBC42_2.0.9.jar -DgroupId=com.amazonaws -DartifactId=AthenaJDBC42 -Dversion=2.0.9 -Dpackaging=jar 