package com.example.root.projecttest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/04/12
 * desc:
 * version:1.0
 */
public class OnlineMusicBean implements Serializable {

    /**
     * 不一定定义所有的字段名，只定义你需要的就可以了
     */

    private String result;
    private int code;
    private DataBean data;  // 该 bean 类名随便取，但是变量名必须和返回值一样


    // 该 bean 可以定义在外部也可以定义在内部，不过在哪都需要是 public，
    // 不然外部取不到值。且为了可以在四大组件传值，所以所有的都必须实现 Serializable 接口
    public class DataBean implements Serializable {
        private String songListId;
        private String songListName;
        private String songListPic;
        private int songListCount;
        private long songListPlayCount;
        private String songListDescription;
        private int songListUserId;

        private List<Song> songs;   // 如果是列表就定义 List 即可。同理类名随便取但是变量名必须和返回值一直

        public class Song implements Serializable {
            private String id;
            private String name;
            private String singer;
            private String pic;
            private String lrc;
            private String url;
            private int time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSinger() {
                return singer;
            }

            public void setSinger(String singer) {
                this.singer = singer;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLrc() {
                return lrc;
            }

            public void setLrc(String lrc) {
                this.lrc = lrc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }
        }

        public String getSongListId() {
            return songListId;
        }

        public void setSongListId(String songListId) {
            this.songListId = songListId;
        }

        public String getSongListName() {
            return songListName;
        }

        public void setSongListName(String songListName) {
            this.songListName = songListName;
        }

        public String getSongListPic() {
            return songListPic;
        }

        public void setSongListPic(String songListPic) {
            this.songListPic = songListPic;
        }

        public int getSongListCount() {
            return songListCount;
        }

        public void setSongListCount(int songListCount) {
            this.songListCount = songListCount;
        }

        public long getSongListPlayCount() {
            return songListPlayCount;
        }

        public void setSongListPlayCount(long songListPlayCount) {
            this.songListPlayCount = songListPlayCount;
        }

        public String getSongListDescription() {
            return songListDescription;
        }

        public void setSongListDescription(String songListDescription) {
            this.songListDescription = songListDescription;
        }

        public int getSongListUserId() {
            return songListUserId;
        }

        public void setSongListUserId(int songListUserId) {
            this.songListUserId = songListUserId;
        }

        public List<Song> getSongs() {
            return songs;
        }

        public void setSongs(List<Song> songs) {
            this.songs = songs;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
