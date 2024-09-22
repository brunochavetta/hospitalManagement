package com.example.laboratory4.medication;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Medication")
public class Medication {
    @Id
    private String id;
    private String name;
    private String presentation;
    private String dosage;
    private String instruction;
    private String contraindications;

    public Medication() {
    }

    public Medication(String name, String presentation, String dosage, String instruction, String contraindications) {
        this.name = name;
        this.presentation = presentation;
        this.dosage = dosage;
        this.instruction = instruction;
        this.contraindications = contraindications;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosage() {
        return dosage;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getContraindications() {
        return contraindications;
    }

    @Override
    public String toString() {
        return "\n - Medication ID = " + id +
                "\n - Name = " + name +
                "\n - Presentation = " + presentation +
                "\n - Dosage = " + dosage +
                "\n - Instruction = " + instruction +
                "\n - Contraindications = " + contraindications;
    }
}
