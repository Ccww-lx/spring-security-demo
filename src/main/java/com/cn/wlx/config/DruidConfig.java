package com.cn.wlx.config;

/**
 * Created by Adminn on 2019/6/28.
 */

public class DruidConfig {
 /*   @Autowired
    private Environment env;
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 获取properties中的对应配置信息
        String mapperPackage = env.getProperty("spring.mybatis.mapperLocations");
        String dialect = env.getProperty("spring.mybatis.dialect");
        Properties properties = new Properties();
        properties.setProperty("dialect", dialect);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigurationProperties(properties);
        Resource[] resources = null;
        // 设置MapperLocations路径
        if (!StringUtils.isEmpty(mapperPackage)) {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            resources = resourcePatternResolver.getResources(mapperPackage);
            sessionFactory.setMapperLocations(resources);
        }
        SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
        return sqlSessionFactory;
    }*/
}
