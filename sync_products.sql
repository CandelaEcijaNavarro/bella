-- Precise sync to match design image badges and sections
-- Fixing numero_reseñas to numero_resenas and puntuacion_media to puntuacion_media
DELETE FROM productos;

-- Section 1: RECIÉN LLEGADOS
-- Crema de noche (Oferta)
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Crema de noche', 318.00, 320.00, true, false, '/images/crema_noche.png', 1, 100, true, 'Crema nutritiva para la noche.', 0, 0.0);

-- Labial de brillo suave
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Labial de brillo suave', 320.00, NULL, false, false, '/images/labial_brillo.png', 1, 100, true, 'Labial con acabado brillante.', 0, 0.0);

-- Mascarilla facial refrescante
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Mascarilla facial refrescante', 280.00, NULL, false, false, '/images/mascarilla_facial.png', 1, 100, true, 'Mascarilla hidratante.', 0, 0.0);

-- Aceite nutritivo facial
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Aceite nutritivo facial', 318.00, 420.00, true, false, '/images/aceite_facial.png', 1, 100, true, 'Aceite con extractos naturales.', 0, 0.0);

-- Serum regenerador de día
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Serum regenerador de día', 320.00, NULL, false, true, '/images/serum_dia.png', 1, 100, true, 'Serum hidratante de día.', 0, 0.0);

-- Bálsamo labial hidratante
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Bálsamo labial hidratante', 280.00, NULL, false, false, '/images/balsamo_labial.png', 1, 100, true, 'Bálsamo protector.', 0, 0.0);

-- Section 2: RECOMENDADO PARA TI
-- Crema reafirmante (Oferta)
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Crema reafirmante', 318.00, 420.00, true, false, '/images/crema_reafirmante.png', 1, 100, true, 'Crema con efecto lifting.', 0, 0.0);

-- Tónico equilibrante
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Tónico equilibrante', 320.00, NULL, false, false, '/images/tonico_facial.png', 1, 100, true, 'Tónico para pieles grasas.', 0, 0.0);

-- Limpiador espumoso suave
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Limpiador espumoso suave', 280.00, NULL, false, false, '/images/limpiador_espumoso.png', 1, 100, true, 'Limpiador diario.', 0, 0.0);

-- Exfoliante de micro-bolitas
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Exfoliante de micro-bolitas', 318.00, 420.00, true, false, '/images/exfoliante.png', 1, 100, true, 'Exfoliante profundo.', 0, 0.0);

-- Protector solar FPS 50
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Protector solar FPS 50', 320.00, NULL, false, false, '/images/protector_solar.png', 1, 100, true, 'Protección alta.', 0, 0.0);

-- Contorno de ojos intensivo
INSERT INTO productos (nombre, precio, precio_original, en_oferta, es_nuevo, imagen, id_categoria, stock, activo, descripcion, numero_resenas, puntuacion_media)
VALUES ('Contorno de ojos intensivo', 280.00, NULL, false, false, '/images/contorno_ojos.png', 1, 100, true, 'Reduce ojeras.', 0, 0.0);
