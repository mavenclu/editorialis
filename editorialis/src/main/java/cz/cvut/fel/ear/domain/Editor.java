package cz.cvut.fel.ear.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "editors")
public class Editor extends BaseUserEntity {

    @OneToMany(mappedBy = "editor")
    private List<Manuscript> managedManuscripts;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    protected Editor() {
        super();
    }

    public static class EditorBuilder{
        private List<Manuscript> managedManuscripts;
        private Category category;
        private String email;
        private String phoneNumber;
        private String firstName;
        private String lastName;


        public EditorBuilder(){}

        public EditorBuilder withManagedManuscripts(List<Manuscript> manuscripts){
            this.managedManuscripts = manuscripts;
            return this;
        }

        public EditorBuilder withCategory(Category category){
            this.category = category;
            return this;
        }

        public EditorBuilder withEmail(String email){
             this.email = email;
             return this;
        }

        public EditorBuilder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EditorBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public EditorBuilder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Editor build(){
            Editor ed = new Editor();
            ed.setCategory(category);
            ed.setManagedManuscripts(managedManuscripts);
            ed.setEmail(email);
            ed.setPhoneNumber(phoneNumber);
            ed.setFirstName(firstName);
            ed.setLastName(lastName);
            return ed;
        }

    }
}
