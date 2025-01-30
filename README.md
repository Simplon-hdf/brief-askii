```
   _____              ___________   .__.__ 
  /  _  \            /   _____/  | _|__|__|
 /  /_\  \   ______  \_____  \|  |/ /  |  |
/    |    \ /_____/  /        \    <|  |  |
\____|__  /         /_______  /__|_ \__|__|
        \/                  \/     \/      
```


Database schema.

## Guide d'importation de la base de données

### Prérequis

* PostgreSQL installé sur votre machine
* Les droits d'administration sur PostgreSQL

### Étapes d'importation

1. **Créer une nouvelle base de données :**

   ```sql
   CREATE DATABASE askii;
   ```

2. **Importer le script SQL :**

   Exécutez la commande suivante pour importer le script SQL dans la base de données :

 

```sql
\c askii

CREATE TABLE product(
   product_id VARCHAR(50),
   name VARCHAR(50),
   price VARCHAR(50),
   PRIMARY KEY(product_id)
);

CREATE TABLE cart(
   cart_id VARCHAR(50),
   save_date VARCHAR(50),
   PRIMARY KEY(cart_id)
);

CREATE TABLE product_cart(
   product_id VARCHAR(50),
   cart_id VARCHAR(50),
   quantity VARCHAR(50),
   PRIMARY KEY(product_id, cart_id),
   FOREIGN KEY(product_id) REFERENCES product(product_id),
   FOREIGN KEY(cart_id) REFERENCES cart(cart_id)
);
   ```


### Vérification

Pour vérifier que l'importation s'est bien passée, vous pouvez exécuter la commande suivante :

```sql
SELECT * FROM information_schema.tables 
WHERE table_schema = 'public';
```

Cette commande devrait afficher la liste de toutes les tables créées dans la base de données "askii".

### Exemple de schéma de base de données

Voici un exemple de schéma de base de données que vous pouvez utiliser :

```sql
INSERT INTO product (product_id, name, price) VALUES
('P001', 'Ski Set (Skis + Bindings)', '499.99'),
('P002', 'Ski Boots', '299.99'),
('P003', 'Ski Poles', '49.99'),
('P004', 'Snowboard', '399.99'),
('P005', 'Snowboard Boots', '249.99'),
('P006', 'Ski Goggles', '79.99'),
('P007', 'Ski Helmet', '99.99'),
('P008', 'Ski Jacket', '199.99'),
('P009', 'Ski Pants', '149.99'),
('P010', 'Base Layer Top', '59.99'),
('P011', 'Base Layer Bottoms', '59.99'),
('P012', 'Ski Socks (3-pack)', '24.99'),
('P013', 'Avalanche Beacon', '299.99'),
('P014', 'Ski Backpack', '89.99'),
('P015', 'Hand Warmers', '9.99'),
('P016', 'Neck Gaiter', '19.99'),
('P017', 'Ski Wax', '14.99'),
('P018', 'Ski Tuning Kit', '49.99'),
('P019', 'Snowshoes', '129.99'),
('P020', 'Ski Pass Holder', '14.99'),
('P021', 'Ski Boot Bag', '39.99'),
('P022', 'Ski Lock', '19.99'),
('P023', 'Goggles Case', '29.99'),
('P024', 'Ski Tuning Vise', '79.99'),
('P025', 'Ski Gloves', '39.99'),
('P026', 'Ski Mittens', '49.99'),
('P027', 'Thermal Gloves', '29.99'),
('P028', 'Ski Map', '9.99'),
('P029', 'Ski Resort Guidebook', '19.99'),
('P030', 'Ski Boot Dryer', '59.99');
```


The architecture brief, by Yohan and Boris.