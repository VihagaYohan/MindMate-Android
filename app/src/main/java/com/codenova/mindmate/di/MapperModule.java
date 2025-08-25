package com.codenova.mindmate.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import com.codenova.mindmate.data.mapper.UserMapper;

@Module
@InstallIn(SingletonComponent.class)
public class MapperModule {

    @Provides
    @Singleton
    public UserMapper provideUserMapper(){
        return new UserMapper();
    }
}
