DROP TABLE FASTLANEVEHICLE_USER_CREDENTIALS CASCADE CONSTRAINTS;
DROP TABLE USER_CAR_DETAILS CASCADE CONSTRAINTS;
DROP TABLE FASTLANEVEHICLE_USER CASCADE CONSTRAINTS;
DROP TABLE ADMIN_LOGIN CASCADE CONSTRAINTS;
DROP TABLE CAR_DETAILS;
DROP TABLE BIKE_DETAILS;
DROP TABLE USER_BIKE_DETAILS;
DROP TABLE CAR_SCHEME;
DROP TABLE BIKE_SCHEME;


DROP SEQUENCE hibernate_sequence;
DROP SEQUENCE carid_sequence;
DROP SEQUENCE regid_sequence;
DROP SEQUENCE regid_sequence1;
DROP SEQUENCE bikeid_sequence;
DROP SEQUENCE bikeregid_sequence;


--------------------------------------------------------SEQUENCE-----------------------------------------------------------------------------------
CREATE SEQUENCE carid_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE hibernate_sequence START WITH 1007 INCREMENT BY 1;
CREATE SEQUENCE regid_sequence START WITH 1120 INCREMENT BY 1;
CREATE SEQUENCE regid_sequence1 START WITH 1001 INCREMENT BY 1;

CREATE SEQUENCE bikeid_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE bikeregid_sequence START WITH 9006 INCREMENT BY 1;

--------------------------------------------------------SELECT-----------------------------------------------------------------------------------
select *from FASTLANEVEHICLE_USER;
select *from ADMIN_LOGIN;
select *from CAR_DETAILS;
select *from USER_CAR_DETAILS;   
select *from BIKE_DETAILS;
select *from USER_BIKE_DETAILS;   


--------------------------------------------------------USER LOGIN TABLE-----------------------------------------------------------------------------------
CREATE TABLE FASTLANEVEHICLE_USER(       
                              USER_ID NUMBER(10) NOT NULL ,
						      NAME VARCHAR2(30) NOT NULL ,
						      USER_NAME VARCHAR2(30) NOT NULL,
							  EMAIL_ID VARCHAR2(25) NOT NULL ,
							  PASSWORD VARCHAR2(20) NOT NULL,
							  MOBILE_NUMBER NUMBER(10) NOT NULL ,
							  DATE_OF_BIRTH DATE NOT NULL,
							  CONSTRAINT INFYVEHICLE_USER_ID_PK20 PRIMARY KEY (USER_ID) ,									  
							  CONSTRAINT INFYVEHICLE_EMAIL_ID_UNQ10 UNIQUE (EMAIL_ID) ,
							  CONSTRAINT INFYVEHICLE_MOBILE_UNQ10 UNIQUE (MOBILE_NUMBER),
							   CONSTRAINT INFYVEHICLE_USER_NAME_UNQ12 UNIQUE (USER_NAME)
							  );

--------------------------------------------------------ADMIN LOGIN TABLE-----------------------------------------------------------------------------------							 
CREATE TABLE ADMIN_LOGIN(
                         USER_NAME VARCHAR2(30) NOT NULL,
                         PASSWORD VARCHAR2(30) NOT NULL,
                         CONSTRAINT ADMIN_LOGIN_USER_NAME_PK2 PRIMARY KEY (USER_NAME),
                         CONSTRAINT ADMIN_LOGIN_PASSWORD_UNQ13 UNIQUE (PASSWORD)
);


--------------------------------------------------------CAR DETAILS AND USER CAR DETAILS TABLE-----------------------------------------------------------------------------------
CREATE TABLE CAR_DETAILS(
                         CAR_ID INTEGER ,
                         CAR_PURCHASE_CITY VARCHAR2(30) NOT NULL,
                         CAR_BRAND VARCHAR2(10) NOT NULL,
                         CAR_MODEL VARCHAR2(30) NOT NULL,
                         CAR_PRICE DECIMAL(11,2) NOT NULL,
                         ENGINE_CAPACITY NUMBER(20) NOT NULL,
                         CONSTRAINT CAR_DETAILS_CAR_ID_PK20 PRIMARY KEY(CAR_ID)
                         
                         );
                         

CREATE TABLE USER_CAR_DETAILS(
                              REG_ID INTEGER,
                              USERNAME VARCHAR2(30) NOT NULL,
                              USER_PURCHASE_CITY VARCHAR(30) NOT NULL,
                              USER_CAR_PURCHASE_DATE DATE NOT NULL,
                              USER_CAR_BRAND  VARCHAR2(10) NOT NULL,
                              USER_CAR_MODEL VARCHAR2(30) NOT NULL,
                              USER_CAR_PRICE DECIMAL(11,2) NOT NULL,
                              USER_CAR_IDV DECIMAL(11,2) NOT NULL,
                              USER_CAR_FINAL_PREMIUM DECIMAL(11,2),
                              USER_CAR_POLICY_NUMBER INTEGER,
                              USER_CAR_POLICY_DATE DATE,
                              USER_CAR_EXPIRY_DATE DATE,
                              CLAIM_STATUS VARCHAR2(50) DEFAULT 'CALIM NOW',
                              POLICY_TYPE VARCHAR2(15),
                              CONSTRAINT USER_CAR_DETAILS_REG_ID_PK20 PRIMARY KEY(REG_ID)
                              );   
                              
                              
--------------------------------------------------------BIKE DETAILS AND USER BIKE DETAILS TABLE-----------------------------------------------------------------------------------                              
CREATE TABLE BIKE_DETAILS(
                         BIKE_ID INTEGER ,
                         BIKE_PURCHASE_CITY VARCHAR2(30) NOT NULL,
                         BIKE_BRAND VARCHAR2(30) NOT NULL,
                         BIKE_MODEL VARCHAR2(30) NOT NULL,
                         BIKE_PRICE DECIMAL(11,2) NOT NULL,
                         CONSTRAINT BIKE_DETAILS_BIKE_ID_PK20 PRIMARY KEY(BIKE_ID)

                         );      
                         
CREATE TABLE USER_BIKE_DETAILS(
                              REG_ID INTEGER,
                              USERNAME VARCHAR2(30) NOT NULL,
                              USER_PURCHASE_CITY VARCHAR(30) NOT NULL,
                              USER_BIKE_PURCHASE_DATE DATE NOT NULL,
                              USER_BIKE_BRAND  VARCHAR2(30) NOT NULL,
                              USER_BIKE_MODEL VARCHAR2(30) NOT NULL,
                              USER_BIKE_PRICE DECIMAL(11,2) NOT NULL,
                              USER_BIKE_IDV DECIMAL(11,2) NOT NULL,
                              USER_BIKE_FINAL_PREMIUM DECIMAL(11,2),
                              USER_BIKE_POLICY_NUMBER INTEGER,
                              USER_BIKE_POLICY_DATE DATE,
                              USER_BIKE_EXPIRY_DATE DATE,
                              CLAIM_STATUS VARCHAR2(50) DEFAULT 'CLAIM NOW',
                              POLICY_TYPE VARCHAR2(15),
                              CONSTRAINT USER_BIKE_DETAILS_REG_ID_PK20 PRIMARY KEY(REG_ID)
                              );   

--------------------------------------------------------CAR and BIKE DETAILS TABLE-----------------------------------------------------------------------------------                              
CREATE TABLE CAR_SCHEME(
                         SCHEME_CAR_NO VARCHAR2(1) NOT NULL ,
                         SCHEME_NAME VARCHAR2(500) NOT NULL,
                         CONSTRAINT CAR_SCHEME_NO_PK20 PRIMARY KEY(SCHEME_CAR_NO)

                         );    

CREATE TABLE BIKE_SCHEME(
                         SCHEME_BIKE_NO VARCHAR2(1) NOT NULL ,
                         SCHEME_BIKE_NAME VARCHAR2(500) NOT NULL,
                         CONSTRAINT BIKE_SCHEME_NO_PK20 PRIMARY KEY(SCHEME_BIKE_NO)

                         );    

INSERT INTO CAR_SCHEME VALUES('A','Benefits Covered Basic-Own Damage + Third Party Road Side Assistance');
INSERT INTO CAR_SCHEME VALUES('B','Benefits Covered Basic-Own Damage + Third Party Road Side Assistance Accident Cover For Passengers Engine Protect');

INSERT INTO BIKE_SCHEME VALUES('A','Benefits Covered Basic-Own Damage + Third Party Road Side Assistance');
INSERT INTO BIKE_SCHEME VALUES('B','Benefits Covered Basic-Own Damage + Third Party Road Side Assistance Accident Cover For Passengers Engine Protect');
         

--------------------------------------------------------INSERT USER LOGIN-----------------------------------------------------------------------------------                         
INSERT INTO FASTLANEVEHICLE_USER VALUES(1006, 'Yash Kapur','yashu', 'yash_kapur@gmail.com','yash%123', 9988774455, '22-DEC-1987');

--------------------------------------------------------USER CAR DETAILS INSERT-----------------------------------------------------------------------------------
INSERT INTO USER_CAR_DETAILS VALUES(regid_sequence.nextval,'yashu','BANGALORE-KARNATAKA','22-DEC-1987','Maruti','800 AC',260669,242549,13013,1021,'5-MAY-2017','5-OCT-2017','In processing','Basic');

--------------------------------------------------------USER BIKE DETAILS INSERT-----------------------------------------------------------------------------------
INSERT INTO USER_BIKE_DETAILS VALUES(bikeregid_sequence.nextval,'yashu','BANGALORE-KARNATAKA','22-DEC-1987','Suzuki','GT 125',37624,242549,13013,1022,'5-MAY-2017','5-OCT-2017','CLAIM NOW','Basic');

--------------------------------------------------------ADMIN LOGIN INSERT-----------------------------------------------------------------------------------
INSERT INTO ADMIN_LOGIN VALUES('admin','admin');

--------------------------------------------------------CAR DETAILS INSERT-----------------------------------------------------------------------------------
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Maruti','800 AC',260669,796);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Maruti','800 AC',244241,796);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Maruti','800 AC',269142,796);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Maruti','800 AC',257728,796);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Maruti','800 AC',249137,796);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Maruti','800 AC',244025,796);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Maruti','Baleno LXI',607501,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Maruti','Baleno LXI',607501,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Maruti','Baleno LXI',607501,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Maruti','Baleno LXI',607501,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Maruti','Baleno LXI',607501,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Maruti','Baleno LXI',607501,998);



INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Maruti','Vitara Breeza VDI',750000,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Maruti','Vitara Breeza VDI',762000,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Maruti','Vitara Breeza VDI',830000,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Maruti','Vitara Breeza VDI',830000,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Maruti','Vitara Breeza VDI',804021,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Maruti','Vitara Breeza VDI',801211,1248);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Maruti','Swift Dzire LDI',617151,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Maruti','Swift Dzire LDI',480902,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Maruti','Swift Dzire LDI',680075,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Maruti','Swift Dzire LDI',478567,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Maruti','Swift Dzire LDI',679918,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Maruti','Swift Dzire LDI',552343,1248);



INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Maruti','Swift LDI',630815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Maruti','Swift LDI',650625,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Maruti','Swift LDI',674864,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Maruti','Swift LDI',603235,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Maruti','Swift LDI',667111,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Maruti','Swift LDI',653886,1248);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Maruti','Wagon R AX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Maruti','Wagon R AX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Maruti','Wagon R AX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Maruti','Wagon R AX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Maruti','Wagon R AX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Maruti','Wagon R AX',438079,998);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Hyundai','Creta 1.4 CRDI L',967983,1591);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Hyundai','Creta 1.4 CRDI L',946939,1591);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hyundai','Creta 1.4 CRDI L',1080000,1591);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Hyundai','Creta 1.4 CRDI L',963774,1591);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Hyundai','Creta 1.4 CRDI L',1020000,1591);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Hyundai','Creta 1.4 CRDI L',967983,1591);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Hyundai','Grand i10 ASTA 1.2 AT',640451,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Hyundai','Grand i10 ASTA 1.2 AT',646856,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hyundai','Grand i10 ASTA 1.2 AT',657085,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Hyundai','Grand i10 ASTA 1.2 AT',712800,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Hyundai','Grand i10 ASTA 1.2 AT',638978,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Hyundai','Grand i10 ASTA 1.2 AT',640541,1197);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Hyundai','Elite i20 ASTA 1.2',709948,1396);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Hyundai','Elite i20 ASTA 1.2',697547,1396);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hyundai','Elite i20 ASTA 1.2',688834,1396);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Hyundai','Elite i20 ASTA 1.2',709948,1396);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Hyundai','Elite i20 ASTA 1.2',728451,1396);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Hyundai','Elite i20 ASTA 1.2',7171047,1396);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Hyundai','i10 MAGNA',448735,1086);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Hyundai','i10 MAGNA',447898,1086);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hyundai','i10 MAGNA',448307,1086);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Hyundai','i10 MAGNA',459551,1086);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Hyundai','i10 MAGNA',445155,1086);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Hyundai','i10 MAGNA',435318,1086);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Hyundai','Verna 1.4 CRDI',794000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Hyundai','Verna 1.4 CRDI',780000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hyundai','Verna 1.4 CRDI',824000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Hyundai','Verna 1.4 CRDI',814000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Hyundai','Verna 1.4 CRDI',784000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Hyundai','Verna 1.4 CRDI',789012,1582);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Hyundai','Xcent 1.2',542800,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Hyundai','Xcent 1.2',552000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hyundai','Xcent 1.2',642000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Hyundai','Xcent 1.2',602000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Hyundai','Xcent 1.2',552000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Hyundai','Xcent 1.2',542000,1197);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Honda','Honda City 1.5',834080,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Honda','Honda City 1.5',850000,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Honda','Honda City 1.5',859000,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Honda','Honda City 1.5',900000,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Honda','Honda City 1.5',850789,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Honda','Honda City 1.5',840090,1497);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Honda','Amaze 1.2 EX',563000,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Honda','Amaze 1.2 EX',558089,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Honda','Amaze 1.2 EX',603012,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Honda','Amaze 1.2 EX',573000,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Honda','Amaze 1.2 EX',590000,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Honda','Amaze 1.2 EX',556755,1198);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Honda','Honda BRV VX I-DTEC DIESEL',879000,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Honda','Honda BRV VX I-DTEC DIESEL',999080,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Honda','Honda BRV VX I-DTEC DIESEL',889089,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Honda','Honda BRV VX I-DTEC DIESEL',907000,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Honda','Honda BRV VX I-DTEC DIESEL',789077,1497);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Honda','Honda BRV VX I-DTEC DIESEL',889000,1497);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Honda','Honda Brio',469000,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Honda','Honda Brio',519000,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Honda','Honda Brio',438000,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Honda','Honda Brio',479019,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Honda','Honda Brio',466600,1198);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Honda','Honda Brio',468700,1198);


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Honda','Honda BRV S I-DTEC PETROL',778123,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Honda','Honda BRV S I-DTEC PETROL',815090,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Honda','Honda BRV S I-DTEC PETROL',790000,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Honda','Honda BRV S I-DTEC PETROL',756010,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Honda','Honda BRV S I-DTEC PETROL',775078,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Honda','Honda BRV S I-DTEC PETROL',825000,1199);



INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','ISUZU','MU 7',2630815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','ISUZU','MU 7',2650625,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','ISUZU','MU 7',2674864,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','ISUZU','MU 7',2603235,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','ISUZU','MU 7',2667111,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','ISUZU','MU 7',2653886,1248);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','ISUZU','D MAX V Cross',1030815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','ISUZU','D MAX V Cross',1030815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','ISUZU','D MAX V Cross',1030815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','ISUZU','D MAX V Cross',1030815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','ISUZU','D MAX V Cross',1030815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','ISUZU','D MAX V Cross',1030815,1248);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','ISUZU','MU 7 Base Bs 3',1630815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','ISUZU','MU 7 Base Bs 3',1650625,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','ISUZU','MU 7 Base Bs 3',1674864,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','ISUZU','MU 7 Base Bs 3',1603235,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','ISUZU','MU 7 Base Bs 3',1667111,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','ISUZU','MU 7 Base Bs 3',1653886,1248);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','ISUZU','MU 7 High Bs 3',1830815,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','ISUZU','MU 7 High Bs 3',1950625,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','ISUZU','MU 7 High Bs 3',1674864,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','ISUZU','MU 7 High Bs 3',1603235,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','ISUZU','MU 7 High Bs 3',1667111,1248);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','ISUZU','MU 7 High Bs 3',1653886,1248);


        ---------------Mahindra-----------

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Mahindra','Bolero LX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Mahindra','Bolero LX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Mahindra','Bolero LX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Mahindra','Bolero LX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Mahindra','Bolero LX',438079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Mahindra','Bolero LX',438079,998);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Mahindra','E20 T0',538079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Mahindra','E20 T0',538079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Mahindra','E20 T0',538079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Mahindra','E20 T0',538079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Mahindra','E20 T0',538079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Mahindra','E20 T0',538079,998);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Mahindra','Jeep 10str',338079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Mahindra','Jeep 10str',338079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Mahindra','Jeep 10str',338079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Mahindra','Jeep 10str',338079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Mahindra','Jeep 10str',338079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Mahindra','Jeep 10str',438079,998);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Mahindra','Scorpio 2.6 GLX',738079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Mahindra','Scorpio 2.6 GLX',738079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Mahindra','Scorpio 2.6 GLX',738079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Mahindra','Scorpio 2.6 GLX',738079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Mahindra','Scorpio 2.6 GLX',738079,998);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Mahindra','Scorpio 2.6 GLX',738079,998);
            --------------------Skoda-----------------

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Skoda','Laura Laura RS',1794000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Skoda','Laura Laura RS',1780000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Skoda','Laura Laura RS',1824000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Skoda','Laura Laura RS',1814000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Skoda','Laura Laura RS',1784000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Skoda','Laura Laura RS',1789012,1582);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Skoda','Octavia L',1494000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Skoda','Octavia L',1380000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Skoda','Octavia L',1424000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Skoda','Octavia L',1414000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Skoda','Octavia L',1484000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Skoda','Octavia L',1389012,1582);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Skoda','Rapid 1.5',794000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Skoda','Rapid 1.5',780000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Skoda','Rapid 1.5',824000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Skoda','Rapid 1.5',814000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Skoda','Rapid 1.5',784000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Skoda','Rapid 1.5',789012,1582);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Skoda','Superb L&K',2794000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Skoda','Superb L&K',2780000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Skoda','Superb L&K',2824000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Skoda','Superb L&K',2814000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Skoda','Superb L&K',2784000,1582);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Skoda','Superb L&K',2789012,1582);


           --------------------Tata------------------
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Tata','BOLT XE Diesel',442800,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Tata','BOLT XE Diesel',452000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Tata','BOLT XE Diesel',442000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Tata','BOLT XE Diesel',402000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Tata','BOLT XE Diesel',452000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Tata','BOLT XE Diesel',542000,1197);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Tata','Indigo GLS',442800,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Tata','Indigo GLS',452000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Tata','Indigo GLS',442000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Tata','Indigo GLS',402000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Tata','Indigo GLS',452000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Tata','Indigo GLS',542000,1197);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Tata','Magic',242800,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Tata','Magic',252000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Tata','Magic',242000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Tata','Magic',202000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Tata','Magic',252000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Tata','Magic',242000,1197);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','Tata','NANO LX',142800,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','Tata','NANO LX',152000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','Tata','NANO LX',142000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','Tata','NANO LX',102000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','Tata','NANO LX',152000,1197);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','Tata','NANO LX',142000,1197);

                ------------------ fiat---------------


INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','fiat','Linea Imotion 1.4',578123,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','fiat','Linea Imotion 1.4',615090,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','fiat','Linea Imotion 1.4',590000,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','fiat','Linea Imotion 1.4',656010,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','fiat','Linea Imotion 1.4',575078,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','fiat','Linea Imotion 1.4',525000,1199);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','fiat','Adventure 1.9 D',678123,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','fiat','Adventure 1.9 D',615090,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','fiat','Adventure 1.9 D',590000,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','fiat','Adventure 1.9 D',756010,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','fiat','Adventure 1.9 D',575078,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','fiat','Adventure 1.9 D',625000,1199);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','fiat','Palio GTX',478123,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','fiat','Palio GTX',415090,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','fiat','Palio GTX',490000,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','fiat','Palio GTX',456010,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','fiat','Palio GTX',475078,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','fiat','Palio GTX',425000,1199);

INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'BANGALORE-KARNATAKA','fiat','UNO 1.0',278123,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'NEW DELHI-DELHI','fiat','UNO 1.0',215090,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'MUMBAI-MAHARASHTRA','fiat','UNO 1.0',290000,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'CHENNAI-TAMILNADU','fiat','UNO 1.0',256010,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'PUNE-MAHARASHTRA','fiat','UNO 1.0',275078,1199);
INSERT INTO CAR_DETAILS VALUES(carid_sequence.nextval,'HYDERABAD-TELANGANA','fiat','UNO 1.0',225000,1199);

--------------------------------------------------------BIKE DETAILS INSERT-----------------------------------------------------------------------------------

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Hero Honda','CD 100',30024);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Hero Honda','CD 100',31200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hero Honda','CD 100',32400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Hero Honda','CD 100',29134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Hero Honda','CD 100',36660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Hero Honda','CD 100',30700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Hero Honda','Passion',36624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Hero Honda','Passion',37200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hero Honda','Passion',37400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Hero Honda','Passion',39134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Hero Honda','Passion',36660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Hero Honda','Passion',38700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Hero Honda','Karizma Red Cast',63624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Hero Honda','Karizma Red Cast',64200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hero Honda','Karizma Red Cast',67400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Hero Honda','Karizma Red Cast',66134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Hero Honda','Karizma Red Cast',66660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Hero Honda','Karizma Red Cast',68700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Hero Honda','Joy',30624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Hero Honda','Joy',30200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hero Honda','Joy',31400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Hero Honda','Joy',32134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Hero Honda','Joy',30660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Hero Honda','Joy',30700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Hero Honda','Splendor Disc',33624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Hero Honda','Splendor Disc',35200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Hero Honda','Splendor Disc',41400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Hero Honda','Splendor Disc',39134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Hero Honda','Splendor Disc',38660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Hero Honda','Splendor Disc',40700);




INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Bajaj Auto','Avenger',52624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Bajaj Auto','Avenger',53200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Bajaj Auto','Avenger',51400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Bajaj Auto','Avenger',52134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Bajaj Auto','Avenger',56660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Bajaj Auto','Avenger',55700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Bajaj Auto','4S Champion',33624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Bajaj Auto','4S Champion',33200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Bajaj Auto','4S Champion',31400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Bajaj Auto','4S Champion',32134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Bajaj Auto','4S Champion',36660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Bajaj Auto','4S Champion',35700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Bajaj Auto','Boxer 125',36624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Bajaj Auto','Boxer 125',33200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Bajaj Auto','Boxer 125',37400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Bajaj Auto','Boxer 125',32134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Bajaj Auto','Boxer 125',36660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Bajaj Auto','Boxer 125',35700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Bajaj Auto','Bravo',32624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Bajaj Auto','Bravo',33200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Bajaj Auto','Bravo',31400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Bajaj Auto','Bravo',32134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Bajaj Auto','Bravo',36660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Bajaj Auto','Bravo',33700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Suzuki','GT 125',37624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Suzuki','GT 125',38200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Suzuki','GT 125',37400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Suzuki','GT 125',38134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Suzuki','GT 125',36660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Suzuki','GT 125',35700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Suzuki','SlingShot',37624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Suzuki','SlingShot',38200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Suzuki','SlingShot',37400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Suzuki','SlingShot',38134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Suzuki','SlingShot',37660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Suzuki','SlingShot',35700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Suzuki','MAX 100R',30624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Suzuki','MAX 100R',30200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Suzuki','MAX 100R',32400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Suzuki','MAX 100R',31134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Suzuki','MAX 100R',32660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Suzuki','MAX 100R',30700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Suzuki','Heat Heat ALLOY',31624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Suzuki','Heat Heat ALLOY',30200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Suzuki','Heat Heat ALLOY',32400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Suzuki','Heat Heat ALLOY',31134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Suzuki','Heat Heat ALLOY',30660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Suzuki','Heat Heat ALLOY',30700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Royal EnField','BULLET 500',126624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Royal EnField','BULLET 500',130200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Royal EnField','BULLET 500',132400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Royal EnField','BULLET 500',121134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Royal EnField','BULLET 500',130660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Royal EnField','BULLET 500',130700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Royal EnField','CLASSIC classic 350',126624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Royal EnField','CLASSIC classic 350',110200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Royal EnField','CLASSIC classic 350',112400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Royal EnField','CLASSIC classic 350',121134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Royal EnField','CLASSIC classic 350',110660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Royal EnField','CLASSIC classic 350',110700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Royal EnField','THUNDERBIRD 350',116624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Royal EnField','THUNDERBIRD  350',115700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Royal EnField','THUNDERBIRD  350',115700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Royal EnField','THUNDERBIRD  350',121134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Royal EnField','THUNDERBIRD  350',110660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Royal EnField','THUNDERBIRD  350',115700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','BMW','R1200 GS Adventure',2116624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','BMW','R1200 GS Adventure',2115700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','BMW','R1200 GS Adventure',2115700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','BMW','R1200 GS Adventure',2121134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','BMW','R1200 GS Adventure',2810660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','BMW','R1200 GS Adventure',2115700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','BMW','F6 50 GS',816624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','BMW','F6 50 GS',835700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','BMW','F6 50 GS',845700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','BMW','F6 50 GS',821134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','BMW','F6 50 GS',810660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','BMW','F6 50 GS',915700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','BMW','R Nine T',916624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','BMW','R Nine T',915700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','BMW','R Nine T',845700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','BMW','R Nine T',821134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','BMW','R Nine T',810660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','BMW','R Nine T',915700);


INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Harley Davidson','Street 750',316624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Harley Davidson','Street 750',315700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Harley Davidson','Street 750',345700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Harley Davidson','Street 750',321134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Harley Davidson','Street 750',410660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Harley Davidson','Street 750',375700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Harley Davidson','DYNA Super Glide',616624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Harley Davidson','DYNA Super Glide',615700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Harley Davidson','DYNA Super Glide',645700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Harley Davidson','DYNA Super Glide',621134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Harley Davidson','DYNA Super Glide',620660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Harley Davidson','DYNA Super Glide',675700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Harley Davidson','Breakout 103',1016624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Harley Davidson','Breakout 103',1016624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Harley Davidson','Breakout 103',1016624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Harley Davidson','Breakout 103',1016624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Harley Davidson','Breakout 103',1016624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Harley Davidson','Breakout 103',1016624);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Harley Davidson','FatBoy Motorcycle',816624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Harley Davidson','FatBoy Motorcycle',815700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Harley Davidson','FatBoy Motorcycle',845700);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Harley Davidson','FatBoy Motorcycle',721134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Harley Davidson','FatBoy Motorcycle',810660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Harley Davidson','FatBoy Motorcycle',975700);

                            ---------------Go Green----------------
                            
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Go Green','Kimaya Electric Bike',37624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Go Green','Kimaya Electric Bike',38200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Go Green','Kimaya Electric Bike',37400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Go Green','Kimaya Electric Bike',38134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Go Green','Kimaya Electric Bike',37660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Go Green','Kimaya Electric Bike',35700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Go Green','Kohra Electric Bike',47624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Go Green','Kohra Electric Bike',38200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Go Green','Kohra Electric Bike',37400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Go Green','Kohra Electric Bike',38134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Go Green','Kohra Electric Bike',37660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Go Green','Kohra Electric Bike',35700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Go Green','Kavach Electric Bike',57624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Go Green','Kavach Electric Bike',48200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Go Green','Kavach Electric Bike',47400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Go Green','Kavach Electric Bike',48134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Go Green','Kavach Electric Bike',47660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Go Green','Kavach Electric Bike',45700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','Go Green','Sunoti Electric Bike',47624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','Go Green','Sunoti Electric Bike',48200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','Go Green','Sunoti Electric Bike',47400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','Go Green','Sunoti Electric Bike',48134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','Go Green','Sunoti Electric Bike',47660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','Go Green','Sunoti Electric Bike',45700);

                          --------------------Yamaha------------------
  
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','YAMAHA','FZ Self Cast Disc',57624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','YAMAHA','FZ Self Cast Disc',58200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','YAMAHA','FZ Self Cast Disc',57400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','YAMAHA','FZ Self Cast Disc',58134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','YAMAHA','FZ Self Cast Disc',57660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','YAMAHA','FZ Self Cast Disc',55700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','YAMAHA','Alpha STD',67624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','YAMAHA','Alpha STD',68200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','YAMAHA','Alpha STD',67400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','YAMAHA','Alpha STD',68134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','YAMAHA','Alpha STD',67660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','YAMAHA','Alpha STD',65700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','YAMAHA','DRAG Star 400',57624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','YAMAHA','DRAG Star 400',58200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','YAMAHA','DRAG Star 400',57400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','YAMAHA','DRAG Star 400',58134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','YAMAHA','DRAG Star 400',57660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','YAMAHA','DRAG Star 400',55700);

INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'BANGALORE-KARNATAKA','YAMAHA','Fazer DLX',37624);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'NEW DELHI-DELHI','YAMAHA','Fazer DLX',38200);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'MUMBAI-MAHARASHTRA','YAMAHA','Fazer DLX',37400);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'CHENNAI-TAMILNADU','YAMAHA','Fazer DLX',38134);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'PUNE-MAHARASHTRA','YAMAHA','Fazer DLX',37660);
INSERT INTO BIKE_DETAILS VALUES(bikeid_sequence.nextval,'HYDERABAD-TELANGANA','YAMAHA','Fazer DLX',35700);