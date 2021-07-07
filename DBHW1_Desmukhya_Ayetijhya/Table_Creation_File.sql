use MovieSystem;
CREATE TABLE Movies(
	[MovieID] [int] NOT NULL IDENTITY(1,1),
	[MovieTitle] [varchar](100) NOT NULL,
	[ReleaseDate] [date] NOT NULL,
	[Price] [money] NOT NULL,
	[Rating] [decimal](5, 2) NOT NULL,
	[Genre] [varchar](20) NULL,
	CONSTRAINT PK_Movies PRIMARY KEY(MovieID)
	)

CREATE TABLE Orders(
	[OrderID] [int] NOT NULL IDENTITY(1,1),
	[RentalDate] [date] NOT NULL,
	[ReturnDate] [date] NOT NULL,
	[MovieID] [int] NOT NULL,
	[NetAmount] [money] NOT NULL,
	[Discount] [decimal](5, 2) NULL,
	[GrossAmount] [money] NOT NULL,
	CONSTRAINT PK_Orders PRIMARY KEY (OrderID),
	CONSTRAINT FK_Orders_Movies FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)
)