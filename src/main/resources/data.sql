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
insert into sitio(id,nombre)values(10,'Bodega de Herramientas');
insert into sitio(id,nombre)values(11,'Bodega de Limpieza');
insert into sitio(id,nombre)values(12,'Comedor');
insert into sitio(id,nombre)values(13,'Educación Física');
insert into sitio(id,nombre)values(14,'Instrumentos Musicales');
insert into sitio(id,nombre)values(15,'Oficina de Orientación');
insert into sitio(id,nombre)values(16,'Pasillos');
--Usuarios
insert into usuario(id,identificacion,nombre,primer_apellido,segundo_apellido,usuario,clave,puesto,activo,id_sitio)values(1,'206340152','Herman','Barrantes','Viquez','herman','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Desarrollador',true,1);
insert into usuario(id,identificacion,nombre,primer_apellido,segundo_apellido,usuario,clave,puesto,activo,id_sitio)values(2,'106340152','Eilyn','Salazar','Miranda','eilyn','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Desarrollador',true,1);
insert into usuario(id,identificacion,nombre,primer_apellido,segundo_apellido,usuario,clave,puesto,activo,id_sitio)values(3,'306340152','Prueba','Prueba','Prueba','prueba','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Prueba',true,1);
--Perfiles
--insert into perfil(id,nombre)values(1,'ADMIN');
--insert into perfil(id,nombre)values(2,'DIRECTOR');
--insert into perfil(id,nombre)values(3,'PROFESOR');
--UsuarioPerfil
insert into usuario_perfil(id_usuario,perfil)values(1,'ADMIN');
insert into usuario_perfil(id_usuario,perfil)values(1,'DIRECTOR');
insert into usuario_perfil(id_usuario,perfil)values(2,'DIRECTOR');
insert into usuario_perfil(id_usuario,perfil)values(3,'PROFESOR');
--Correo
insert into correo(id,correo,tipo)values(1,'barrantesgerman@gmail.com','PERSONAL');
insert into correo(id,correo,tipo)values(2,'barrantesherman@gmail.com','PERSONAL');
insert into correo(id,correo,tipo)values(10,'epa@gmail.com','TRABAJO');
insert into correo(id,correo,tipo)values(11,'epa@hotmail.com','TRABAJO');
insert into correo(id,correo,tipo)values(12,'empleados@pm.com','TRABAJO');
--Usuario Correo
insert into usuario_correo(id_usuario,id_correo)values(1,1);
insert into usuario_correo(id_usuario,id_correo)values(1,2);
--Telefono
insert into telefono(id,telefono,tipo)values(1,'88922819','CELULAR');
insert into telefono(id,telefono,tipo)values(2,'24655201','CASA');
insert into telefono(id,telefono,tipo)values(10,'22223333','TRABAJO');
insert into telefono(id,telefono,tipo)values(11,'33334444','TRABAJO');
insert into telefono(id,telefono,tipo)values(12,'44445555','TRABAJO');
--Usuario Telefono
insert into usuario_telefono(id_usuario,id_telefono)values(1,1);
insert into usuario_telefono(id_usuario,id_telefono)values(1,2);
--Categoria
insert into categoria(id,descripcion)values(1,'Oficina');
insert into categoria(id,descripcion)values(2,'Aula');
insert into categoria(id,descripcion)values(3,'Cocina');
--Proveedor
insert into proveedor(id,nombre,direccion,sitio_web)values(1,'EPA Curridabat','200mts Oeste de la Pops de Curridabat.','http://www.epa.com');
insert into proveedor(id,nombre,direccion,sitio_web)values(2,'Pequeño Mundo Curridabat','200mts Este de el Mall San Pedro.','http://www.pequeñomundo.com');
--Proveedor Correo
insert into proveedor_correo(id_proveedor,id_correo)values(1,10);
insert into proveedor_correo(id_proveedor,id_correo)values(1,11);
insert into proveedor_correo(id_proveedor,id_correo)values(2,12);
--Proveedor Telefono
insert into proveedor_telefono(id_proveedor,id_telefono)values(1,10);
insert into proveedor_telefono(id_proveedor,id_telefono)values(1,11);
insert into proveedor_telefono(id_proveedor,id_telefono)values(2,12);

--Articulo
insert into articulo(id,descripcion,id_categoria)values(1,'Pupitre',2);
insert into articulo(id,descripcion,id_categoria)values(2,'Server', 1);
insert into articulo(id,descripcion,id_categoria)values(3,'Licuadora', 3);

--Activo Detalle
insert into activo_detalle(id,descripcion,modelo,marca)values(1,'Grande, color verde',null,null);
insert into activo_detalle(id,descripcion,modelo,marca)values(2,null,'123','HP');
insert into activo_detalle(id,descripcion,modelo,marca)values(3,'Licuadora Industrial','XPS300','Oster');

--Activos
insert into activo(id,placa,serie,estado,id_sitio,id_articulo,id_detalle)values(1,'EAC-01','123', 'BUENO',12,3,1);
insert into activo(id,placa,serie,estado,id_sitio,id_articulo,id_detalle)values(2,'EAD-01','456', 'BUENO',1,2,2);
insert into activo(id,placa,serie,estado,id_sitio,id_articulo,id_detalle)values(3,'EA-01','789', 'BUENO',2,3,3);

--Ordenes de Compra
insert into orden_compra(id,factura,fecha,estado,id_proveedor)values(1,'Factura 1','2015-01-01', 'COMPRADO',1);
insert into orden_compra(id,factura,fecha,estado,id_proveedor)values(2,'Factura 2','2015-01-02', 'RECIBIDO',1);
insert into orden_compra(id,factura,fecha,estado,id_proveedor)values(3,'Factura 3','2015-01-03', 'APLICADO',2);

--Orden de Compra Detalle
insert into orden_compra_detalle(id,id_orden_compra,precio,cantidad,id_articulo)values(1,1,2000, 2,1);
insert into orden_compra_detalle(id,id_orden_compra,precio,cantidad,id_articulo)values(2,1,3000, 1,2);
insert into orden_compra_detalle(id,id_orden_compra,precio,cantidad,id_articulo)values(3,2,1000, 2,1);