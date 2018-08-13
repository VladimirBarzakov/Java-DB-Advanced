package app.exam.service.api;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.entities.Item;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface ItemsService {
    void create(ItemJSONImportDTO itemJSONImportDTO);
}
