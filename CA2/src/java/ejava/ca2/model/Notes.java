/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sanskar
 */
@Entity
@Table(name = "notes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notes.findAll", query = "SELECT n FROM Notes n"),
    @NamedQuery(name = "Notes.findByUserid", query = "SELECT n FROM Notes n WHERE n.notesPK.userid = :userid"),
    @NamedQuery(name = "Notes.findByContent", query = "SELECT n FROM Notes n WHERE n.content = :content"),
    @NamedQuery(name = "Notes.findByTitle", query = "SELECT n FROM Notes n WHERE n.notesPK.title = :title"),
    @NamedQuery(name = "Notes.findByCategory", query = "SELECT n FROM Notes n WHERE n.notesPK.category = :category")})
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotesPK notesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Notes() {
    }

    public Notes(NotesPK notesPK) {
        this.notesPK = notesPK;
    }

    public Notes(NotesPK notesPK, String content) {
        this.notesPK = notesPK;
        this.content = content;
    }

    public Notes(String userid, String title, String category) {
        this.notesPK = new NotesPK(userid, title, category);
    }

    public NotesPK getNotesPK() {
        return notesPK;
    }

    public void setNotesPK(NotesPK notesPK) {
        this.notesPK = notesPK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notesPK != null ? notesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notes)) {
            return false;
        }
        Notes other = (Notes) object;
        if ((this.notesPK == null && other.notesPK != null) || (this.notesPK != null && !this.notesPK.equals(other.notesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejava.ca2.model.Notes[ notesPK=" + notesPK + " ]";
    }
    
}