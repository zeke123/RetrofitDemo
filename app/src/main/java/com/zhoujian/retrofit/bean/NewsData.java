package com.zhoujian.retrofit.bean;

import java.util.List;

/**
 * Created by zhoujian on 2016/12/28.
 */

public class NewsData {

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {

        private boolean status;
        private int total;
        private List<ListBean> list;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {


            private int count;
            private String description;
            private int fcount;
            private int id;
            private String img;
            private int infoclass;
            private String keywords;
            private int rcount;
            private long time;
            private String title;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getFcount() {
                return fcount;
            }

            public void setFcount(int fcount) {
                this.fcount = fcount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getInfoclass() {
                return infoclass;
            }

            public void setInfoclass(int infoclass) {
                this.infoclass = infoclass;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public int getRcount() {
                return rcount;
            }

            public void setRcount(int rcount) {
                this.rcount = rcount;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "count=" + count +
                        ", description='" + description + '\'' +
                        ", fcount=" + fcount +
                        ", id=" + id +
                        ", img='" + img + '\'' +
                        ", infoclass=" + infoclass +
                        ", keywords='" + keywords + '\'' +
                        ", rcount=" + rcount +
                        ", time=" + time +
                        ", title='" + title + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "list=" + list +
                    ", status=" + status +
                    ", total=" + total +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
