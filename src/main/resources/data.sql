INSERT INTO SAMPLE VALUES('1', 'VALUE1');
INSERT INTO SAMPLE VALUES('2', 'VALUE2');

INSERT INTO TECH_SERVICE_TYPE (NAME, DESCRIPTION) VALUES ('ANTIVIRUS','Antivirus');
INSERT INTO TECH_SERVICE_TYPE (NAME, DESCRIPTION) VALUES ('BACKUP','Backup');
INSERT INTO TECH_SERVICE_TYPE (NAME, DESCRIPTION) VALUES ('SCREEN_SHARE','Screen Share');
INSERT INTO TECH_SERVICE_TYPE (NAME, DESCRIPTION) VALUES ('SERVICE_DEVICE','Service Device');

INSERT INTO TECH_SERVICE (NAME, PRICE, OPERATING_SYSTEM, TYPE_NAME) VALUES (
'SERVICE_DEVICE', 4.00, 'ANY', 'SERVICE_DEVICE');
INSERT INTO TECH_SERVICE (NAME, PRICE, OPERATING_SYSTEM, TYPE_NAME) VALUES (
'ANTIVIRUS_FOR_WINDOWS', 5.00, 'WINDOWS', 'ANTIVIRUS');
INSERT INTO TECH_SERVICE (NAME, PRICE, OPERATING_SYSTEM, TYPE_NAME) VALUES (
'ANTIVIRUS_FOR_MAC', 7.00, 'MAC', 'ANTIVIRUS');
INSERT INTO TECH_SERVICE (NAME, PRICE, OPERATING_SYSTEM, TYPE_NAME) VALUES (
'BACKUP', 3.00, 'ANY', 'BACKUP');
INSERT INTO TECH_SERVICE (NAME, PRICE, OPERATING_SYSTEM, TYPE_NAME) VALUES (
'SCREEN_SHARE', 1.00, 'ANY', 'SCREEN_SHARE');



