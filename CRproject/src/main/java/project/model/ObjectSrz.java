package project.model;

import javax.persistence.*;

@Entity
@Table(name = "sobj")
public class ObjectSrz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "byte_obj")
    private byte[] obj;

    public ObjectSrz() {
    }

    public ObjectSrz(Long id, byte[] obj) {
        this.id = id;
        this.obj = obj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getObj() {
        return this.obj;
    }

    public void setObj(byte[] obj) {
        this.obj = obj;
    }
}
