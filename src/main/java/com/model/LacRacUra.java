package com.model;

import com.model.modelsForCreationCommands.util.CreationCommand;

import java.util.Map;

public class LacRacUra implements CreationCommand {

    private Lac lac;
    private Rac rac;
    private Ura ura;

    public LacRacUra(String lac, String ura) {
        this.lac = new Lac(lac);
        this.rac = new Rac(lac);
        this.ura = new Ura(ura);
    }

    class Lac {
        private String lac;

        public Lac(String lac) {
            this.lac = lac;
        }

        public String getLac() {
            return lac;
        }

        public void setLac(String lac) {
            this.lac = lac;
        }

        @Override
        public String toString() {
            return "crn RncFunction=1,LocationArea=" + lac + "\n" +
                    "lac " + lac + "\n" +
                    "t3212 40"+ '\n' +
                    "end" + "\n\n";

        }
    }

    class Rac {
        private String lac;

        public Rac(String lac) {
            this.lac = lac;
        }

        public String getLac() {
            return lac;
        }

        public void setLac(String lac) {
            this.lac = lac;
        }

        @Override
        public String toString() {
            return "crn LocationArea=" + lac +",RoutingArea=2" + "\n" +
                    "rac 2" + "\n" +
                    "end" + "\n\n";
        }
    }

    class Ura {
        private String uraIdentity;

        public Ura(String uraIdentity) {
            this.uraIdentity = uraIdentity;
        }

        public String getUraIdentity() {
            return uraIdentity;
        }

        public void setUraIdentity(String uraIdentity) {
            this.uraIdentity = uraIdentity;
        }

        @Override
        public String toString() {
            return "crn RncFunction=1,Ura=" + uraIdentity + "\n" +
                    "uraIdentity " + uraIdentity + "\n" +
                    "end" + "\n\n";
        }
    }

    @Override
    public CreationCommand getCreationCommand(Patterns pattern, String source) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, String> getValues() {
        return null;
    }

    @Override
    public Patterns getType() {
        return null;
    }

    @Override
    public String toString() {
        return lac + "\n" +
                rac + "\n" +
                ura + "\n";
    }
}
