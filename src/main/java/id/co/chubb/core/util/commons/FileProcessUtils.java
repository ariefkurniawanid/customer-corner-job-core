package id.co.chubb.core.util.commons;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileProcessUtils {
    public Set<String> compareName(List<String> nameSource, List<String> nameDest, Boolean isSame) {
        Set<String> sameName = new HashSet<>(nameSource);
        sameName.retainAll(nameDest);
        if (!isSame) {
            Set<String> differentName = new HashSet<>(nameSource);
            differentName.addAll(nameDest);
            differentName.removeAll(sameName);
            return differentName;
        }
        return sameName;
    }
}
