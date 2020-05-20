package com.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class LogListenServer implements ApplicationListener<ContextRefreshedEvent>
{

	@Autowired
	private WebSocketTemp w;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0)
	{
		new Thread()
		{
			private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						String msg=sf.format(new Date())+" 扫描中......";
						w.sendMessage(msg);
						Thread.sleep(1000);
					} catch (Exception e)
					{
						w.sendMessage(sf.format(new Date())+e.toString());
					}
				}
			}
		}.start();

	}

}
