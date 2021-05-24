package main.repository;

import main.domain.Audit;

import java.util.List;

public class AuditFileRepository extends AbstractFileRepository<Integer, Audit>{

    public  AuditFileRepository(String fileName){
        super(fileName);
    }

    @Override
    protected Audit extractEntity(List<String> attributes) {
        String action = attributes.get(1);
        String timeStamp = attributes.get(2);
        Audit audit = new Audit(action, timeStamp);
        return audit;
    }

    @Override
    protected String createEntityAsString(Audit entity) {

        return entity.getId()+ ","+ entity.getAction() + "," + entity.getTimeStamp();
    }
}
