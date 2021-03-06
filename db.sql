use cucina; 

-- DBTools Manager Professional (Enterprise Edition)
-- Database Dump for: cucina
-- Backup Generated in: 23/04/2010 18:50:33
-- Database Server Version: MySQL 5.1.26

-- USEGO




-- select * from calendario where month(dataInizio) = 2 and year(curdate()) = year(dataInizio); 


-- select * from calendario where month(dataInizio) = month(curdate()) and year(dataInizio) = 2020; 

 select c.id_edizione, i.id_utente, f.descrizione from calendario c join iscritti i on (c.id_edizione = i.id_edizione) join feedback f on (i.id_utente = f.id_utente) where month(dataInizio) = 5 and year(curdate()) = year(dataInizio);

SET FOREIGN_KEY_CHECKS=0;
-- GO.



--
-- Dumping Tables
--

--
-- Table: amministratori
--
drop table if exists amministratori;
CREATE TABLE `amministratori` (
	`id_amministratore` varchar (50) PRIMARY KEY, 
	`password` varchar (50), 
	`nome` varchar (50), 
	`cognome` varchar (50), 
	`dataNascita` date, 
	`email` varchar (50), 
	`telefono` varchar (50)
); 
-- GO
--
-- Dumping Table Data: amministratori
--
BEGIN;
-- GO
INSERT INTO `amministratori` (`id_amministratore`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('idutente', 'password', 'Admin', 'Bianchi', '1975-02-25', 'administratorSC@gmail.com', '3331234567');
-- GO
INSERT INTO `amministratori` (`id_amministratore`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('marco81', 'marco', 'Marco', 'Rossi', '1957-11-22', 'admScuolaCucina@tiscali.it', '3332456178');
-- GO
COMMIT;
-- GO

--
-- Table: calendario
--
drop table if exists calendario;
CREATE TABLE `calendario` 
(
	`id_edizione` integer (11) PRIMARY KEY AUTO_INCREMENT, 
	`id_corso` integer (11) references catalogo(id_corso), 
	`dataInizio` date, 
	`durata` integer (11), 
	`aula` varchar (50), 
	`docente` varchar (50)
);
-- GO
--
-- Dumping Table Data: calendario
--
BEGIN;
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(92, 87, '2010-05-07', 2, 'Aula 1', 'C. Amato');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(93, 88, '2010-05-11', 1, 'Aula 2', 'F. Beatini');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(94, 90, '2010-05-19', 1, 'Aula 3', 'T. Mita');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(95, 91, '2010-05-03', 1, 'Aula 2', 'D.Priori');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(96, 92, '2010-05-28', 1, 'Aula 1', 'C. Amato');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(97, 93, '2010-05-30', 7, 'Aula 3', 'C. Amato');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(98, 94, '2010-06-01', 5, 'Aula 2', 'D. Priori');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(99, 95, '2010-05-30', 14, 'Aula 1', 'F. Beatini');
-- GO
INSERT INTO `calendario` (`id_edizione`, `id_corso`, `dataInizio`, `durata`, `aula`, `docente`) VALUES(100, 90, '2010-04-28', 7, 'Aula3', 'M. Moretti');
-- GO
INSERT INTO `calendario` (`id_edizione`,  `dataInizio`, `durata`, `aula`, `docente`) VALUES(101, '2020-02-12', 7, 'Aula3', 'M. Moretti');
COMMIT;
-- GO
--
-- Index: delCorso
--
ALTER TABLE `cucina`.`calendario` ADD INDEX delCorso (id_corso);
-- GO
--
-- Table: catalogo
--
drop table if exists catalogo;
CREATE TABLE `catalogo` (
	`id_corso` integer (11) PRIMARY KEY AUTO_INCREMENT, 
	`titolo` varchar (50), 
	`id_categoria` integer (11) references categoria(id_categoria), 
	`numeroMaxPartecipanti` integer (11), 
	`costo` double (13,2), 
	`descrizione` varchar (2000)
);
-- GO

--
-- Dumping Table Data: catalogo
--
BEGIN;
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(87, 'Sfiziosità di carne', 50, 15, 100.00, 'Squisiti bocconcini, piccole preparazioni classiche e ricette innovative. Un appetitoso viaggio attraverso i tanti modi di preparare e gustare la carne rossa e bianca, tra fantasia e tradizione, semplicità e raffinatezza.\r\n\r\nImportante!!!\r\nIl presente corso verrà erogato nel/i giorno/i\r\nindicato/i nel \'Calendario Corsi\' e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\nPro: poche calorie, colorati, facili da preparare, appetitosi\r\nContro: una volta assaggiati vi richiederanno sempre porzioni abbondanti. da realizzare\r\n\r\nImportante!!!\r\nIl presente corso verrà erogato nel/i giorno/i\r\nindicato/i nel \"Calendario Corsi\" e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(88, 'Sfiziosi primi vegetariani', 49, 20, 70.00, 'Pro: poche calorie, colorati, facili da preparare, appetitosi\r\nContro: una volta assaggiati vi richiederanno sempre porzioni abbondanti. da realizzare\r\n\r\nImportante!!!\r\nIl presente corso verrà erogato nel/i giorno/i\r\nindicato/i nel \"Calendario Corsi\" e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(90, 'Dessert al piatto', 53, 20, 75.00, 'Un dolce deve essere bello, sorprendente, affascinante, intrigante, profumatoal primo boccone dobbiamo desiderare subito il secondo. Ci saranno combinazioni di sapore mai forzate ma che sorprenderanno le vostre papille gustative. Otterrete da semplici ingredienti e da preparazioni del tutto tradizionali, ottimi e innovativi dessert.\r\n\r\nImportante!!!\r\nIl presente corso verrà erogato nel/i giorno/i\r\nindicato/i nel \"Calendario Corsi\" e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(91, 'Antipastini di mare', 48, 20, 130.00, 'Sfiziosi antipasti, pieni di sapore e profumi, ricette facilmente realizzabili, insolite e gustosissime.\r\n\r\nImportante!!!\r\nIl presente corso verrà erogato nel/i giorno/i\r\nindicato/i nel \"Calendario Corsi\" e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(92, 'Paste con verdure', 49, 15, 80.00, 'La primavera regala alla pasta soluzioni divertenti di sapore, all\'insegna dell\'originalità. Fave, melanzane, piselli, asparagi e tanti altri si sposeranno con garganelli, orecchiette, tagliolini... un gustoso matrimonio di sapori.\r\n\r\nImportante!!!\r\nIl presente corso verrà erogato nel/i giorno/i\r\nindicato/i nel \"Calendario Corsi\" e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n\r\n\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(93, 'Corso di cucina (base)', 54, 20, 250.00, 'Un corso fondamentale, a cadenza settimanale, un vero e proprio punto di partenza per conoscere i procedimenti e le lavorazioni indispensabili per cucinare. Ogni incontro sarà l\'occasione per apprendere, attraverso la realizzazione di un menu completo, piccoli segreti sull\'esecuzione di piatti basilari o sull\'acquisto e la scelta degli alimenti, la loro lavorazione e infine i \"trucchi\" dello chef che saranno il vostro asso nella manica. Un corso importante attraverso cui capire e \"provare\" la cucina, che degusterete con l\'abbinamento dei vini. Possibilità di esercitarsi, nel corso della lezione, accanto allo chef. A fine corso sarà rilasciato un attestato.\r\n\r\nImportante!!!\r\nIl  presente corso verrà erogato con una cadenza settimanale a partire dal giorno\r\nindicato nel \"Calendario Corsi\"  e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(94, 'Corso di cucina\r\n(avanzato)\r\n', 55, 20, 260.00, 'Accontentando le numerose richieste di chi ha già superato il livello di base, cinque incontri (a cadenza settimanale) in cui approfondire quegli argomenti che escono dalla conoscenza di base e si avvicinano maggiormente all\'alta cucina. Spazieremo nel mondo delle salse, della cottura a vapore per una cucina leggera ma piena di sapore, dei bolliti fatti ad arte, della pasta fatta in casa con creatività, del cous cous, del pan di Spagna e molte altre ghiottonerie.\r\n\r\nImportante!!!\r\nIl  presente corso verrà erogato con una cadenza settimanale a partire dal giorno\r\nindicato nel \"Calendario Corsi\"  e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(95, 'Corso di cucina per cuochi', 55, 15, 500.00, 'Un corso ben strutturato con teoria e pratica. E\' una guida per acquisire le tecniche base di cucina e apprendere l\'aspetto merceologico degli alimenti, l\'organizzazione di una cucina e le sue figure professionali. Un corso ideato per permettere a tutti, in tempi ristretti, di conoscere attraverso l\'esecuzione di ricette base le nozioni fondamentali del cucinare, la costruzione di un menu e la presentazione del piatto. Ogni argomento verrà trattato con la massima cura e correlato da utili dispense, dalle paste alle verdure, dal pesce alla carne, dagli antipasti alla pasticceria e tante altre informazioni, tutte finalizzate a formare una valida figura professionale. Consegna dell\'attestato di partecipazione con valutazione. La Direzione si riserva, a suo insindacabile giudizio, di far frequentare agli allievi più meritevoli stages formativi gratuiti presso aziende della ristorazione.\r\n\r\nImportante!!!\r\nIl presente corso verrà erogato nel/i giorno/i\r\nindicato/i nel \"Calendario Corsi\" e osserverà la durata di quattro ore (ore 18.00-22.00) per ogni giorno indicato.\r\n\r\n');
-- GO
INSERT INTO `catalogo` (`id_corso`, `titolo`, `id_categoria`, `numeroMaxPartecipanti`, `costo`, `descrizione`) VALUES(96, 'Corso di cucina indiana', 54, 25, 750.00, 'Un corso da non perdere');
-- GO
COMMIT;
-- GO
--
-- Index: categoriaDelCorso
--
ALTER TABLE `cucina`.`catalogo` ADD INDEX categoriaDelCorso (id_categoria );
-- GO
--
-- Table: categoria
--
drop table if exists categoria;
CREATE TABLE `categoria` (
	`id_categoria` integer (11) PRIMARY KEY AUTO_INCREMENT, 
	`descrizione` varchar (50)
);
-- GO
--
-- Dumping Table Data: categoria
--
BEGIN;
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(48, 'Antipasti');
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(49, 'Primi piatti');
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(50, 'Secondi piatti');
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(51, 'Contorni');
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(52, 'Frutta');
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(53, 'Dolci');
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(54, 'Cucina amatoriale');
-- GO
INSERT INTO `categoria` (`id_categoria`, `descrizione`) VALUES(55, 'Cucina professionale(corsi per cuochi)');
-- GO
INSERT INTO `categoria` (`descrizione`) VALUES('cucina orientale');
COMMIT;
-- GO
--
-- Table: feedback
--
drop table if exists feedback;
CREATE TABLE `feedback` (
	`id_feedback` integer (11) PRIMARY KEY AUTO_INCREMENT, 
	`id_edizione` integer (11) references calendario(id_edizione), 
	`id_utente` varchar (50) NOT NULL references registrati(id_utente), 
	`descrizione` varchar (50), 
	`voto` integer (11)
    ); 
-- GO
--
-- Dumping Table Data: feedback
--
BEGIN;
-- GO
INSERT INTO `feedback` (`id_feedback`, `id_edizione`, `id_utente`, `descrizione`, `voto`) VALUES(1, 93, 'fausto', 'da consigliare!!!', 8);
-- GO
INSERT INTO `feedback` (`id_feedback`, `id_edizione`, `id_utente`, `descrizione`, `voto`) VALUES(2, 93, 'mauro', 'veramente interessante.', 9);
-- GO
INSERT INTO `feedback` (`id_feedback`, `id_edizione`, `id_utente`, `descrizione`, `voto`) VALUES(3, 98, 'davide', 'ottimi contenuti, ottimo insegnante', 10);
-- GO
INSERT INTO `feedback` (`id_feedback`, `id_edizione`, `id_utente`, `descrizione`, `voto`) VALUES(4, 97, 'Ing_Ruben', 'breve ma ben strutturato', 7);
-- GO
INSERT INTO `feedback` (`id_feedback`, `id_edizione`, `id_utente`, `descrizione`, `voto`) VALUES(5, 99, 'michele', 'esaustivo!!!', 8);
-- GO
COMMIT;
-- GO
--
-- Index: dellEdizione
--
ALTER TABLE `cucina`.`feedback` ADD INDEX dellEdizione (id_edizione );
-- GO
--
-- Index: dellUtente
--
ALTER TABLE `cucina`.`feedback` ADD INDEX dellUtente (id_utente );
-- GO
--
-- Table: iscritti
--
drop table if exists iscritti;
CREATE TABLE `iscritti` (
	`id_edizione` integer (11) NOT NULL DEFAULT 0 references registrati(id_utente), 
	`id_utente` varchar (50) NOT NULL references calendario(id_edizione),
	PRIMARY KEY (`id_edizione`, `id_utente`)
    );
-- GO
--
-- Dumping Table Data: iscritti
--
BEGIN;
-- GO
INSERT INTO `iscritti` (`id_edizione`, `id_utente`) VALUES(93, 'Ing_Ruben');
-- GO
INSERT INTO `iscritti` (`id_edizione`, `id_utente`) VALUES(100, 'veronica');
-- GO
COMMIT;
-- GO
--
-- Index: utenteIscritto
--
ALTER TABLE `cucina`.`iscritti` ADD INDEX utenteIscritto (id_utente );
-- GO
--
-- Table: mail
--
drop table if exists mail;
CREATE TABLE `mail` (
	`id` integer (11) PRIMARY KEY AUTO_INCREMENT, 
	`subject` varchar (50), 
	`data` varchar (50), 
	`body` varchar (200)
);
-- GO
--
-- Dumping Table Data: mail
--
BEGIN;
-- GO
COMMIT;
-- GO
--
-- Table: registrati
--
drop table if exists registrati;
CREATE TABLE `registrati` (
	`id_utente` varchar (50) PRIMARY KEY, 
	`password` varchar (50), 
	`nome` varchar (50), 
	`cognome` varchar (50), 
	`dataNascita` date, 
	`email` varchar (50), 
	`telefono` varchar (50)
);
-- GO
--
-- Dumping Table Data: registrati
--
BEGIN;
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('davide', 'davide', 'Davide', 'Precetti', '1982-08-12', 'davide.precetti@gmail.com', '3391448087');
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('fausto', 'fausto', 'Fausto', 'Paniccia', '1982-03-03', 'pncfausto@libero.it', '3201916480');
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('francesco', 'francesco', 'Francesco', 'Valerio', '1982-04-13', 'francesco.valerio@gmail.com', '3386965410');
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('Ing_Ruben', 'password', 'Ruben', 'Giaccotto', '1981-03-17', 'ruben@giaccotto.it', '3477011366');
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('mar81', '81', 'Marco', 'Rossi', '1981-04-01', 'marcobrucchietti@gmail.com', '33325854118');
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('mauro', 'mauro', 'Mauro', 'Bove', '1981-08-19', 'bove.mauro@gmail.com', '3387972613');
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('michele', 'michele', 'Michele', 'Fiorentino', '1988-01-09', 'mike.fiorentino@tiscali.it', '3402286606');
-- GO
INSERT INTO `registrati` (`id_utente`, `password`, `nome`, `cognome`, `dataNascita`, `email`, `telefono`) VALUES('veronica', 'veronica', 'Veronica', 'Romani', '1983-07-24', 'veve-83@hotmail.it', '3336448818');
-- GO
COMMIT;
-- GO
-- GO
--
-- Dumping Triggers
--
SET FOREIGN_KEY_CHECKS=1;
-- GO

