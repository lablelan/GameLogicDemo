CREATE DATABASE IF NOT EXISTS game DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use game;

CREATE TABLE IF NOT EXISTS `timer` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `playerId` varchar(11) NOT NULL DEFAULT '0',
    `name` varchar(64) NOT NULL,
    `lastTick` bigint(20) NOT NUll,
    PRIMARY KEY ( `id` ),
    INDEX (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;