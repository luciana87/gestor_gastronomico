# proyecto-gestor-gastronomico
Desarrollo de una API REST para locales gastronómicos

Gestiona el control de stock de materias primas, los proveedores, los productos y la atención al cliente.

Hasta el momento se han realizado las siguientes tablas (con sus respectivas relaciones entre ellas):

   proveedor --> Endpoints: [POST-GET-GETById-DELETEAll-DELETE-PUT-PATCH]
   pedido_al_proveedor --> Endpoints: [POST-GET-GETByProveedor-DELETE-PATCH]
   item_pedido

   materia_prima --> Endpoints: [POST-GET-GETById-DELETE(sin terminar)-PATCH]
   ingrediente
   producto --> Endpoints: [POST-GET-GETById-PATCH-DELETE]

Se comenzó a trabajar con otras 3 tablas, pero no se alcanzó a finalizar toda la lógica para la fecha de la entrega. Las tablas son:

   ticket
   detalle_ticket
   mesa

