Método HTTP     	Ruta	                    Acción
POST	        /productos/guardar	            Agrega un producto nuevo
GET	            /productos/listar	            Lista todos los productos
GET	            /productos/buscarPorId/{id}	    Busca un producto por ID
GET	            /productos/buscarPorNombre	    Busca productos por nombre
PUT	            /productos/actualizar/{id}	    Actualiza un producto
DELETE	        /productos/eliminar/{id}	    Elimina un producto

# Json producto
{
  "nomProducto": "Super ochos",
  "catProduc": "Comida",
  "cantProducto": 15
}