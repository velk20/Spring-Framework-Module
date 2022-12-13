insert into users(id,email,first_name,Last_name,image_url,is_active,password)
VALUES (1,'angel@admin','Angel','Mladenov',null,1,'767974a427df93b6f7004b1446ea031600c714863cfc6d156aef8c6b02d967a1245e4cb1d46b496c');

insert into brands(id, name)
values (1, 'Ford'),
       (2, 'Toyota'),
       (3, 'Fiat'),
       (4, 'BMW'),
       (5, 'Mercedes'),
       (6, 'Citroen'),
       (7, 'Nissan'),
       (8, 'Kia');

insert into models(id,name,category_enum,start_year,end_year,brand_id,image_url)
values (1,'Fiesta','CAR',1976,null,1,'https://www.motopfohe.bg/files/news/archive/2017/08/blob-server.jpg'),
        (2,'Corolla','CAR',1991,null,2,'https://upload.wikimedia
        .org/wikipedia/commons/8/89/2019_Toyota_Corolla_Design_VVT-i_HEV_1.8_Front.jpg'),
       (3,'Bravo','CAR',1989,null,3,'https://www.motopfohe.bg/files/news/archive/2017/08/blob-server.jpg'),
         (4,'E60','CAR',2001,2010,4,'https://upload.wikimedia.org/wikipedia/commons/c/c5/2003_BMW_520i_SE_2.2_Front
.jpg');


