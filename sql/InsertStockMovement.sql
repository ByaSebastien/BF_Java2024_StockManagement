CREATE EXTENSION pgcrypto;

CREATE OR REPLACE PROCEDURE InsertStockMovement(
    IN articleId UUID,
    IN movementType VARCHAR(20),
    IN quantity INT
)
    LANGUAGE plpgsql
AS $$
DECLARE
currentStock INT;
    stockExists INT;
BEGIN
    -- Vérifier si un stock existe pour cet article
SELECT COUNT(*) INTO stockExists
FROM Stock
WHERE article_id = articleId;

-- Si le stock n'existe pas, le créer
IF stockExists = 0 THEN
        INSERT INTO Stock (id, current_quantity, article_id,created_at,updated_at)
        VALUES (gen_random_uuid(), 0, articleId,now(),now());
        currentStock := 0;
ELSE
        -- Récupérer le stock actuel si le stock existe
SELECT current_quantity INTO currentStock
FROM Stock
WHERE article_id = articleId;
END IF;

    -- Ajuster le stock en fonction du type de mouvement
    IF movementType = 'STOCK_IN' OR movementType = 'STOCK_POSITIVE_CORRECTION' OR movementType = 'STOCK_RECALL' THEN
        currentStock := currentStock + quantity;
    ELSIF movementType = 'STOCK_OUT' OR movementType = 'STOCK_NEGATIVE_CORRECTION' OR movementType = 'STOCK_RETURN' OR movementType = 'STOCK_MISSING' THEN
        currentStock := currentStock - quantity;
END IF;

    -- Mettre à jour la quantité dans la table Stock
UPDATE Stock
SET current_quantity = currentStock,
    updated_at = now()
WHERE article_id = articleId;

-- Insérer le mouvement de stock
INSERT INTO stock_movement (id, movement_type, quantity, movement_date, article_id)
VALUES (gen_random_uuid(), movementType, quantity, NOW(), articleId);

END $$;
