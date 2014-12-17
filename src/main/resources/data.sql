insert into usuario(id,identificacion,nombre,primer_apellido,segundo_apellido,usuario,clave,puesto,activo)values(1,'206340152','Herman','Barrantes','Viquez','herman','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Desarrollador',true);
insert into perfil(id,nombre)values(1,'ADMIN');
insert into usuario_perfil(id_usuario,id_perfil)values(1,1);