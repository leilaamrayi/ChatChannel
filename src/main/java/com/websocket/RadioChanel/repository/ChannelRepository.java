package com.websocket.RadioChanel.repository;


import com.websocket.RadioChanel.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChannelRepository extends JpaRepository <Channel,Long> {

}
