--Usuarios
insert into usuario(id,identificacion,nombre,primer_apellido,segundo_apellido,usuario,clave,puesto,activo)values(1,'206340152','Herman','Barrantes','Viquez','herman','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Desarrollador',true);
insert into usuario(id,identificacion,nombre,primer_apellido,segundo_apellido,usuario,clave,puesto,activo)values(2,'106340152','Eilyn','Salazar','Miranda','eilyn','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Desarrollador',true);
insert into usuario(id,identificacion,nombre,primer_apellido,segundo_apellido,usuario,clave,puesto,activo)values(3,'306340152','Prueba','Prueba','Prueba','prueba','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Prueba',true);
--Perfiles
insert into perfil(id,nombre)values(1,'ADMIN');
insert into perfil(id,nombre)values(2,'DIRECTOR');
insert into perfil(id,nombre)values(3,'PROFESOR');
--UsuarioPerfil
insert into usuario_perfil(id_usuario,id_perfil)values(1,1);
insert into usuario_perfil(id_usuario,id_perfil)values(2,1);
insert into usuario_perfil(id_usuario,id_perfil)values(3,3);
--Sitio
insert into sitio(id,nombre)values(1,'Dirección');
insert into sitio(id,nombre)values(2,'Aula 1');
insert into sitio(id,nombre)values(3,'Aula 2');
insert into sitio(id,nombre)values(4,'Aula 3');
insert into sitio(id,nombre)values(5,'Aula 4');
insert into sitio(id,nombre)values(6,'Aula 5');
insert into sitio(id,nombre)values(7,'Aula 6');
insert into sitio(id,nombre)values(8,'Aula 7');
insert into sitio(id,nombre)values(9,'Aula 8');
--Categoria
insert into categoria(id,descripcion)values(1,'Oficina');
insert into categoria(id,descripcion)values(2,'Aula');
insert into categoria(id,descripcion)values(3,'Cocina');