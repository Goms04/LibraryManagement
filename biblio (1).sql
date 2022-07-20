-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  lun. 09 août 2021 à 09:55
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `biblio`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

DROP TABLE IF EXISTS `adherents`;
CREATE TABLE IF NOT EXISTS `adherents` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `tel` int(15) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`id`, `nom`, `prenom`, `profession`, `tel`, `email`) VALUES
(1, 'MANOU', 'Nicaada Gratien', 'Etudiant', 93535459, 'manougratien@gmail.com'),
(2, 'AKOTA', 'Benoît', 'Etudiant', 96541235, 'akota.komi@yaourt.com'),
(3, 'GOMS', 'Goms', 'Etudiant', 93535456, 'gomsgoms@yahoo.com'),
(5, 'AGBA', 'Yannick', 'Etudiant', 92560314, 'yannickagba@outlook.com'),
(7, 'TITI', 'Tutu', 'commercant', 90124569, 'tititute@yahoo.com'),
(8, 'TOTO', 'Tata', 'qdnjfq', 10299, '@jzncj.com');

-- --------------------------------------------------------

--
-- Structure de la table `bd`
--

DROP TABLE IF EXISTS `bd`;
CREATE TABLE IF NOT EXISTS `bd` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `dessinateur` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `bd`
--

INSERT INTO `bd` (`id`, `titre`, `dessinateur`) VALUES
(1, 'Les profs', 'KIshimoto'),
(2, 'Boom Beach', 'Bernard Dadié'),
(3, 'Titeuf', 'Zagabongo'),
(5, 'jouyt', 'Goms');

-- --------------------------------------------------------

--
-- Structure de la table `dico`
--

DROP TABLE IF EXISTS `dico`;
CREATE TABLE IF NOT EXISTS `dico` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `dico`
--

INSERT INTO `dico` (`id`, `titre`, `auteur`) VALUES
(1, 'LAROUSSE', 'rousse le blanc'),
(2, 'LE ROBERT', 'Davies Robert'),
(3, 'LaNoblesse', 'Voltaire');

-- --------------------------------------------------------

--
-- Structure de la table `emprunts`
--

DROP TABLE IF EXISTS `emprunts`;
CREATE TABLE IF NOT EXISTS `emprunts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `tel` int(20) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  `dateE` date NOT NULL,
  `dateR` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `emprunts`
--

INSERT INTO `emprunts` (`id`, `nom`, `prenom`, `tel`, `titre`, `auteur`, `dateE`, `dateR`) VALUES
(1, 'AKOTA', 'Benoît', 96541235, 'Programmation web', 'Pascal BODJONA', '2021-08-07', '2021-08-08'),
(2, 'AKOTA', 'Benoît', 96541235, 'Fables', 'La fontaine', '2021-08-07', '2021-08-08'),
(3, 'GOMS', 'Goms', 93535456, 'ville cruelle', 'eza BOTO', '2021-08-05', '2021-08-09'),
(4, 'MANOU', 'Nicaada Gratien', 93535459, 'Archer Bassari', 'AGBA', '2021-08-24', '2021-08-19'),
(5, 'TOTO', 'Tata', 10299, 'ville cruelle', 'eza BOTO', '2021-08-07', '2021-08-09'),
(6, 'TOTO', 'Tata', 10299, 'gria', 'gbati', '2021-08-07', '2021-08-08');

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

DROP TABLE IF EXISTS `journal`;
CREATE TABLE IF NOT EXISTS `journal` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `date` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `journal`
--

INSERT INTO `journal` (`id`, `titre`, `date`) VALUES
(1, 'CITY NEWS', '2021-08-05'),
(2, 'LE VEILLEE', '2021-05-10'),
(3, 'LaVoisine', '2021-08-10'),
(4, 'SIKAA', '2021-08-18'),
(5, 'gumgum', '2021-07-26'),
(6, 'GTUY', '2021-08-05'),
(7, 'qsvfq', '2021-08-06');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  `annee` int(15) NOT NULL,
  `edition` varchar(255) NOT NULL,
  `disponible` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `titre`, `auteur`, `annee`, `edition`, `disponible`) VALUES
(2, 'Climbié tome 1', 'Bernard DADIE', 2004, 'racine', 'non'),
(3, 'La pagne noir', 'Abdoulayee Sadji', 1934, 'bitoven', 'non'),
(4, 'Fables', 'La fontaine', 1988, 'lumiere sombre', 'oui'),
(6, 'Programmation web', 'Pascal BODJONA', 2006, 'course poursuite', 'Oui'),
(7, 'avare', 'MOLIERE', 2010, 'lombre', 'non'),
(8, 'ville cruelle', 'eza BOTO', 2004, 'jounée noires', 'oui'),
(10, 'Le mandat', 'David KLU', 2018, 'ucao', 'Non'),
(14, 'Archer Bassari', 'AGBA', 2015, 'huy', 'Oui'),
(15, 'Doc', 'HTIH', 1850, 'bci nedc', 'Oui'),
(17, 'gria', 'gbati', 210, 'ucao', 'Non');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `utilisateur` varchar(255) NOT NULL,
  `motdepasse` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `Prenom`, `utilisateur`, `motdepasse`, `email`) VALUES
(1, 'MANOU', 'Gratien', 'goms', 'goms', 'manougratien@gmail.com'),
(3, 'KOKO', 'KIKI', 'admin', 'admin', 'admin@.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
