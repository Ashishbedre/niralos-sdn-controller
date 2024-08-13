package com.other.app.niralos_edge.dto;

public class StorageData {
        private String storage;
        private int active;
        private double used_fraction;
        private int shared;
        private long total;
        private int enabled;
        private long used;
        private String content;
        private long avail;
        private String type;

        // Getters and Setters

        public String getStorage() {
            return storage;
        }

        public void setStorage(String storage) {
            this.storage = storage;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public double getUsed_fraction() {
            return used_fraction;
        }

        public void setUsed_fraction(double used_fraction) {
            this.used_fraction = used_fraction;
        }

        public int getShared() {
            return shared;
        }

        public void setShared(int shared) {
            this.shared = shared;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }

        public long getUsed() {
            return used;
        }

        public void setUsed(long used) {
            this.used = used;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getAvail() {
            return avail;
        }

        public void setAvail(long avail) {
            this.avail = avail;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
}
