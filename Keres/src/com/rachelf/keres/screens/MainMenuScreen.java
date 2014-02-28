package com.rachelf.keres.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rachelf.keres.Keres;

public class MainMenuScreen implements Screen {
	private Keres game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton playButton;
	private BitmapFont white;
	private Label heading;
	private int width;
	private int height;
	private Sound select;
	
	public MainMenuScreen(Keres game)
	{
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		this.game = game;
		select = Gdx.audio.newSound(Gdx.files.internal("data/go.wav"));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Table.drawDebug(stage);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println(width);
		System.out.println(height);
		stage.setViewport(width, height, true);
		table.setClip(true);
		table.invalidateHierarchy();
		table.setSize(width, height);
	}

	@Override
	public void show() {
		
		white = new BitmapFont(Gdx.files.internal("fonts/white.fnt"), false);
		stage = new Stage();
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("buttonUp");
		textButtonStyle.down = skin.getDrawable("buttonDown");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = white;
		textButtonStyle.fontColor = Color.WHITE;

		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		playButton = new TextButton("START", textButtonStyle);
		//playButton.scale((int) Gdx.graphics.getWidth() / 10, (int) Gdx.graphics.getHeight() / 10);
		//playButton.pad(5);
		playButton.setSize((int) width / 4, (int) height / 4);
		System.out.println(playButton.getHeight());
		System.out.println(playButton.getWidth());

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				select.play();
				game.setScreen(game.getGameScreen());
			}
		});
		
		LabelStyle headingStyle = new LabelStyle(white, Color.MAGENTA);

		heading = new Label("Keres", headingStyle);
		heading.setFontScale(3);
		table.add(heading);
		table.getCell(heading).spaceBottom(150);
		table.row().fill();
		table.add(playButton);
		table.getCell(playButton).size(playButton.getWidth(), playButton.getHeight());
		table.debug();
		stage.addActor(table);
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		white.dispose();
		skin.dispose();
		atlas.dispose();
		select.dispose();
	
	}

}
