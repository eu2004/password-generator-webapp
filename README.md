# password-generator-webapp

docs:
https://localhost:8090/swagger-ui/index.html

key-cloak setup:

1. Downloading and Installing Keycloak
   docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev
   http://localhost:8080/

3. Creating a Realm
   SpringBootKeycloak

4. Creating a Client
   new Client login-app

4. Creating a Role and a User
   Realm Roles page -> user role
   Users -> create user1
   Assign role user to user user1

6. Generating Access Tokens With Keycloak's API - check SpringBootKeycloak.postman_collection.json
    curl --location --request POST 'http://localhost:8080/realms/SpringBootKeycloak/protocol/openid-connect/token' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'client_id=login-app' \
    --data-urlencode 'username=user1' \
    --data-urlencode 'password=admin' \
    --data-urlencode 'grant_type=password' \
    --data-urlencode 'user_id=b78f7206-5be5-454a-b64b-8ad178f7d39f'

7. Request with token:
   curl --location --request POST 'https://localhost:8090/password-generator' \
   --header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICItSldJQ18zT0gtdGEtZ3N4NU5EVHlyRWJ3dDNUcWwzcGl4M2dPaHlWSlJNIn0.eyJleHAiOjE2ODExMTc1NTEsImlhdCI6MTY4MTExNzI1MSwianRpIjoiNjU5YWFkOTMtMmIzZS00ZjExLTgzMzQtMzQ0YTE0OTdhZmJkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9TcHJpbmdCb290S2V5Y2xvYWsiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiYjc4ZjcyMDYtNWJlNS00NTRhLWI2NGItOGFkMTc4ZjdkMzlmIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoibG9naW4tYXBwIiwic2Vzc2lvbl9zdGF0ZSI6ImFiZDc4MjFjLWQ2ZTgtNDNiYS04MDJiLTU2MWNiMWU5MThiYyIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLXNwcmluZ2Jvb3RrZXljbG9hayIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsibG9naW4tYXBwIjp7InJvbGVzIjpbInVzZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImFiZDc4MjFjLWQ2ZTgtNDNiYS04MDJiLTU2MWNiMWU5MThiYyIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEiLCJnaXZlbl9uYW1lIjoiIiwiZmFtaWx5X25hbWUiOiIiLCJlbWFpbCI6InVzZXIxQGVtYWlsLmNvbSJ9.NYQphetIbf43olNaKrOgOx4WJpb_mkyBusghI4fK_LMyOyDWdrP1j6HnkSpSwsHPvxIz8ZZIOeD0MpQJBTXPfwk0g7wc3zzwJlWwtSEuhzekvY8uefUyY8uFm_1Mk85r6htgk5I-5XKepsU73fWBukQxBT1iyNZkJzfyx57Oy8xCyV17kPzBPWk5ny-dju5N4sGNXivh_opgAVmsnrY_eVBSl4YJQkKlddEmfqkK2iAGGbugCp7ksBnlA6d0VPRdITu0YxUUd1_2KI8KJ_YOIdSb84nZv_YeO642aeZhLOvrHtTvY-90HvXzxqz5tcOUljZQWchVb6a1BegpQgSt1A' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=66D5C885529068D47B230CA6AE5DFACC' \
   --data-raw '{
   "includeLowerCase": true,
   "includeNumbers": true,
   "includeSymbols": true,
   "includeUpperCase": true,
   "length": 16
   }'

Research:
https://www.baeldung.com/spring-boot-keycloak

https enable:
java -Djasypt.encryptor.password=SuperS3ecr3etZ -jar /home/pi/apps/password-generator-webapp/password-generator-webapp-1.2-SNAPSHOT.jar &

/d/jdk-11.0.11/bin/java -Djasypt.encryptor.password=SuperS3ecr3etZ -jar target/password-generator-webapp-1.2-SNAPSHOT.jar


/d/jdk-11.0.11/bin/java -cp ~/.m2/repository/org/jasypt/jasypt/1.9.3/jasypt-1.9.3.jar  org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI password=SuperS3ecr3etZ algorithm=PBEWITHHMACSHA512ANDAES_256 ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator input="pass-gen-web-pass..."

----ENVIRONMENT-----------------

Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 11.0.11+9-LTS-194



----ARGUMENTS-------------------

input: pass-gen-web-pass...
password: SuperS3ecr3etZ
algorithm: PBEWithMD5AndDES



----OUTPUT----------------------

ESw/fX2SaAsX1EzeKSWV7Zas1wuOC/LWnc7vLxQaqHk=

