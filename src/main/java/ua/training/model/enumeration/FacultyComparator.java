package ua.training.model.enumeration;

import ua.training.model.entity.Faculty;

import java.util.Comparator;

/**
 * Represents an enumeration of faculty comparators.
 * Each enum contains a key for value in property file
 * to get correct translation on view and comparator
 * used to sort list of faculties.
 *
 * @author Datsiuk Oleksandr
 * @version 1.5
 * @since 1.0
 */
public enum FacultyComparator implements Comparator<Faculty> {
    AZ("enum.sort.faculties.a.z") {
        public int compare(Faculty o1, Faculty o2) {
            return o1.getName().compareTo(o2.getName());
        }
    },
    ZA("enum.sort.faculties.z.a") {
        public int compare(Faculty o1, Faculty o2) {
            return -AZ.compare(o1, o2);
        }
    },
    AA("enum.sort.faculties.all.amount") {
        public int compare(Faculty o1, Faculty o2) {
            return o1.getAllAmount() - o2.getAllAmount();
        }
    },
    BA("enum.sort.faculties.budget.amount") {
        public int compare(Faculty o1, Faculty o2) {
            return o1.getFreeAmount() - o2.getFreeAmount();
        }
    },
    ;

    private String key;

    FacultyComparator(String name) {
        this.key = name;
    }

    public String getKey() {
        return key;
    }

    public static FacultyComparator getComparator(String s) {
        try {
            return valueOf(s);
        } catch (IllegalArgumentException e) {
            return AZ;
        }
    }
}

