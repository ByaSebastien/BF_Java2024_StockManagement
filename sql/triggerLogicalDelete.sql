CREATE OR REPLACE FUNCTION logical_delete()
    RETURNS TRIGGER AS $$
BEGIN
    -- Au lieu de supprimer la ligne, on met à jour is_deleted à true
UPDATE article SET is_deleted = TRUE WHERE id = OLD.id;

-- Empêcher la suppression physique de l'enregistrement
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_logical_delete
    BEFORE DELETE ON article
    FOR EACH ROW
    EXECUTE FUNCTION logical_delete();