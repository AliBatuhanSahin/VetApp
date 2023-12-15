# Java Spring Boot - Veteriner Yönetim Sistemi Projesi

###  Bu proje Java ve Maven kullanılıp, katmanlı mimari göz önünde bulundurularak geliştirilmiş bir Rest API projesidir.

## Proje Mimarisinde:

- Entities
- Dto Request-Response
- Dao Repository Interface with JPA
- IoC, DI constructor injection
- Anatations (@Entity, @Table, @Id, @OneToMany, @ManyToOne, @ManyToMany)
- Fetch, Cascade Anatations
- Exceptions
- HTTP Response Status Codes (200-OK, 201-Created)
- Hibernate
- PostgreSQL

## UML Diyagramı
![UML](Uml%20Diagram/Uml%20Diagram.png)

## End Points
### Bu proje http://localhost:8080 Port'u üzerinden çalışmaktadır. Alt tarafta bulunan End Point URL'lerini portun sonuna ekleyerek istek atabilirsiniz. Projenin içinde Swagger-ui eklidir. Dilerseniz http://localhost:8080/swagger-ui/index.html#/ adresinden tüm End Pointlere ulaşabilirsiniz.
![Animal](End%20Points/Animal.png)
![Customer](End%20Points/Customer.png)
![Vaccine](End%20Points/Vaccine.png)
![Doctor](End%20Points/Doctor.png)
![Date](End%20Points/Doctor's%20Available%20Date.png)
![Appointment](End%20Points/Appointment.png)

## PostgreSQL Tabloları
### Tabloların örnekleri ver örnek verileri içerikte bulunmaktadır. Dilerseniz proje içerisindeki VetappSQL.sql dosyasını veritabanınıza Import'layarak bu verilere ulaşabilirsiniz.
![Animal](PostgreSQLTables/Animal.png)
![Customer](PostgreSQLTables/Customer.png)
![Doctor](PostgreSQLTables/Doctor.png)
![Vaccine](PostgreSQLTables/Vaccine.png)
![Appointment](PostgreSQLTables/Appointment.png)
![Date](PostgreSQLTables/Available%20Date.png)


