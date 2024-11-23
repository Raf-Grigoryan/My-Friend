package org.example.myfriend.service;

import org.example.myfriend.db.DBConnectionProvider;
import org.example.myfriend.model.Friend;
import org.example.myfriend.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendService {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserService userService = new UserService();

    public void sendRequestFriend(Friend friend) {
        try {
            String sql = "INSERT INTO friend(sender_id,friend_id) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, friend.getSender().getId());
            preparedStatement.setInt(2, friend.getFriend().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Friend> getFriendsBySenderId(int userId) {
        List<Friend> friends = new ArrayList<>();
        try {
            String sql = "SELECT * FROM friend WHERE (sender_id = ? OR friend_id = ?) AND accepted = true";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Friend friend = Friend.builder()
                        .id(resultSet.getInt("id"))
                        .sender(userService.getUserById(resultSet.getInt("sender_id")))
                        .friend(userService.getUserById(resultSet.getInt("friend_id")))
                        .date(DateUtil.sqlStringToDate(resultSet.getString("date")))
                        .accepted(resultSet.getBoolean("accepted"))
                        .build();
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }


    public Friend getFriendBySenderIdAndFriendId(int senderId, int friendId) {
        String sql = "SELECT * FROM friend WHERE sender_id=? AND friend_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, senderId);
            preparedStatement.setInt(2, friendId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Friend.builder()
                            .id(resultSet.getInt("id"))
                            .sender(userService.getUserById(resultSet.getInt("sender_id")))
                            .friend(userService.getUserById(resultSet.getInt("friend_id")))
                            .date(DateUtil.sqlStringToDate(resultSet.getString("date")))
                            .accepted(resultSet.getBoolean("accepted"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeFriendBySenderIdAndFriendId(int senderId, int friendId) {
        try {
            String sql = "DELETE FROM friend WHERE sender_id=? AND friend_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, senderId);
            preparedStatement.setInt(2, friendId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Friend> getFriendRequests(int friendId) {
        List<Friend> friends = new ArrayList<>();
        try {
            String sql = "SELECT * FROM friend WHERE friend_id=? AND accepted = false";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, friendId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Friend friend = Friend.builder()
                        .id(resultSet.getInt("id"))
                        .sender(userService.getUserById(resultSet.getInt("sender_id")))
                        .friend(userService.getUserById(resultSet.getInt("friend_id")))
                        .date(DateUtil.sqlStringToDate(resultSet.getString("date")))
                        .accepted(resultSet.getBoolean("accepted"))
                        .build();
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public void acceptFriend(int senderId, int friendId) {
        try {
            String sql = "UPDATE friend SET accepted=TRUE WHERE sender_id=? AND friend_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, senderId);
            preparedStatement.setInt(2, friendId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
