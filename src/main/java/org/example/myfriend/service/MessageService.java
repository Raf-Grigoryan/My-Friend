package org.example.myfriend.service;

import org.example.myfriend.db.DBConnectionProvider;
import org.example.myfriend.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserService userService = new UserService();

    public void sendMessage(Message message) {
        try {
            String sql = "INSERT INTO message(from_id,to_id,message) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, message.getSender().getId());
            statement.setInt(1, message.getFriend().getId());
            statement.setString(1, message.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessagesBySenderAndFriend(int senderId, int friendId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE (from_id = ? AND to_id = ?) OR (from_id = ? AND to_id = ?) ORDER BY date ASC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, senderId);
            statement.setInt(2, friendId);
            statement.setInt(3, friendId);
            statement.setInt(4, senderId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = Message.builder()
                        .id(resultSet.getInt("id"))
                        .sender(userService.getUserById(resultSet.getInt("from_id")))
                        .friend(userService.getUserById(resultSet.getInt("to_id")))
                        .message(resultSet.getString("message"))
                        .date(resultSet.getDate("date"))
                        .build();
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
