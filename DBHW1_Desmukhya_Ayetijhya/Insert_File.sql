use MovieSystem;

INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Avatar','2009-12-18',9.50,7.8,'Fantasy');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Titanic','1997-11-18',9.60,7.8,'Romance');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Avengers:Endgame','2019-04-26',10.42,8.4,'Science Fiction');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Intersteller','2014-11-07',10.54,8.6,'Science Fiction');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Inception','2010-07-16',10.63,8.8,'Action');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('La La Land','2016-11-09',9.99,8.0,'Romance');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('The Shawshank Redemption','1994-09-22',12.56,9.3,'Crime Fiction');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Alita:Battle Angel','2019-02-08',8.34,7.3,'Science Fiction');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Wonder Woman 1984','2020-12-16',6.23,5.4,'Fantasy');
INSERT INTO Movies(MovieTitle,ReleaseDate,Price,Rating,Genre)
VALUES('Tenet','2020-12-04',8.41,7.4,'Action');


INSERT INTO Orders(RentalDate,ReturnDate,MovieID,NetAmount,Discount,GrossAmount)
VALUES('2021-01-18','2021-01-25',1,9.50,25,9.31);
INSERT INTO Orders(RentalDate,ReturnDate,MovieID,NetAmount,GrossAmount)
VALUES('2021-02-07','2021-02-10',10,8.41,10.34);
INSERT INTO Orders(RentalDate,ReturnDate,MovieID,NetAmount,Discount,GrossAmount)
VALUES('2021-04-05','2021-04-12',7,12.56,25,12.31);